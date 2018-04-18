<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Disk Home Page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="../js/jquery/2.0.0/jquery.min.js"></script>
<script src="../js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="../js/zTree/jquery.ztree.exedit-3.5.min.js"></script>
<script src="../js/jquery/2.0.0/jquery.form.js"></script>
<link href="../css/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet" />

</head>

<body>
	<table id="bodyTb" border=1>
		<tr id="userInfoTr">
			<td id="usernameTd" width=7000>
				Welcome, <span id="usernameSp">${user.username}</span>
			</td>
			<td id="sizeTd" width=2000>
				Total Size: <span id="SizeSp">${disk.size}</span> KB
			</td>
			<td id="usedSizeTd" width=2000>
				Used Size: <span id="usedSizeSp">${disk.usedSize}</span> KB
			</td>
			<td id="fileNumberTd" width=2000>
				File Number: <span id="fileNumberSp">${disk.fileNumber}</span>
			</td>
		</tr>
	</table>

	<table id="fileOperationTb" border=0>
		<tr id="fileOperationTr">
			<td width=2000>
				<input id="downloadBtn" type="button" value="Download"/>
			</td>
			<td width=2000>
			<form id="uploadForm" enctype="multipart/form-data" method="post">
				<input type="file" name="file" "id="file" value="Select File"/>
				<input id="uploadBtn" type="button" value="Upload"/>
			</form>
			</td>
			<td width=2000>
				<input id="mkDirTxt" type="text" value="Name of New Folder Here"/>
				<input id="mkDirBtn" type="button" value="Make New Folder"/>
			</td>
			<td width=2000>
				<input id="DeleteBtn" type="button" value="Delete"/>
			</td>
			<td width=2000>
				<input id="RenameTxt" type="text" value="New Name Here"/>
				<input id="RenameBtn" type="button" value="Rename"/>
			</td>
		</tr>
	</table>
	
	<ul id="fileTree" class="ztree"></ul>
	
</body>

<script type="text/javascript">
var zTree;
//ztree main initialization function
$(document).ready(function(){
	zTree = $.fn.zTree.init($("#fileTree"), setting, zNodes);
	var root = zTree.getNodeByTId("fileTree_1");
	zTree.reAsyncChildNodes(root,"refresh",true);
	$("#root span").data("node_id","fileTree_1").data("file_id",${rootDirectoryID});
	root.isClick = true;
	zTree.selectNode(root);
});
//ztree root node in initialization
var zNodes=[{
	isParent:true,
	name:"My Cloud Storage",
	open:true,
	id:${rootDirectoryID},
	type:"dir"
}];
//data filter function used to pre-process data into appropraite json
dataFilter = function(tId, pNode, files){
		if(files){ //if files are not null
			var file;
			for(var i=0;i<files.length;i++){
				file = files[i];
				console.log(file);
				if(file.dir){ //if is a folder, set type to dir
					file.type="dir";
					file.isParent = true; //set isParent to true
				}else{ //else set type to file type
					var fName = file.name;
					file.type=fName.substr(fName.lastIndexOf(".")+1,fName.length+1);
					file.icon = "../img/fileIcon/" + file.type+".gif"; //set icon to gif path
				}
			}
		}
		return files;
	};
//setting of ztree in initialization
var setting = {
	async:{ //ajax
		enable:true, //enable ajax
		dataFilter:dataFilter, //pre-process data function
		autoParam:["id"],  //parameter of SpringMVC controller function
		url: "listSubfiles" //server-side ajax url
	},
	data:{keep:{parent:true}},
	callback:{ //async callback
		onAsyncSuccess:function(event, treeId, tNode, msg){
			if(tNode.isClick){ //if node is click
				listSubfiles(tNode); //list all sub files
			}
		}
	},
	onClick:function(event, treeId, tNode){ //when node is clicked
		if(!tNode.isDir){  //if node is not dir, return
			return;
		}
		if(tNode.isParent&&tNode.isClick==null){ //if node is parent, set isClick to true
			tNode.isClick=true;
		}
		if(tNode.zAsync==false){ //if async fails, reasync
			zTree.reAsyncChildNodes(tNode,"refresh",true);
		}else{ //else, list all subfiles
			listSubfiles(tNode);
		}
	}
};
$("#downloadBtn").click(function(){
	var selectedNode = zTree.getSelectedNodes()[0];
	var fileId = selectedNode.id;
	window.location.href = "download/" + fileId;
});
$("#uploadBtn").click(function(){
	var selectedNode = zTree.getSelectedNodes()[0];
	var folderId = selectedNode.id;
	var options = {
			url:"upload/"+folderId,
			type:"post",
			data:$('#uploadForm').serialize(),
			processData:false,
			dataType:"text",
			ContentType:false,
			success:function(res){
				if(res=="false"){
					alert("Upload Fail, No Enough Space.");
				}else{
					alert("Upload Success");
					var result = JSON.parse(res);
					console.log(result);
					addFile(result.file, selectedNode);
					$('#usedSizeSp').html(result.usedSize);
					var fileNumber = parseFloat($('#fileNumberSp').html());
					console.log("usedSize "+result.usedSize);
					$('#fileNumberSp').html(fileNumber+1);
				}
			}
	};
	
	$("#uploadForm").ajaxSubmit(options);
});
$("#DeleteBtn").click(function(){
	if(confirm("Please confirm deleting the target file/folder")){					
		var selectedNode = zTree.getSelectedNodes()[0];
		var fileId = selectedNode.id;
		$.ajax({
			url:"delete/"+fileId,
			type:"post",
			dataType:"text",
			processData:false,
			success:function(res){
				zTree.removeNode(selectedNode);
				var root = zTree.getNodeByTId("fileTree_1");
				zTree.selectNode(root);
				$('#usedSizeSp').html(res);
				console.log("delete:"+res);
				var fileNumber = parseFloat($('#fileNumberSp').html());
				$('#fileNumberSp').html(fileNumber-1);
			}
		});
	}
});
$("#mkDirBtn").click(function(){
	var selectedNode = zTree.getSelectedNodes()[0];
	var fileId = selectedNode.id;
	
	$.ajax({
		url:"mkdir/"+fileId,
		type:"post",
		dataType:"json",
		data:"name="+$('#mkDirTxt').val(),
		processData:false,
		//ContentType:undefined,
		success:function(res){
			addFile(res, selectedNode);
		}
	});
});
$("#RenameBtn").click(function(){
	var selectedNode = zTree.getSelectedNodes()[0];
	var fileId = selectedNode.id;
	
	$.ajax({
		url:"rename/"+fileId,
		type:"post",
		data:"newFileName="+$('#RenameTxt').val(),
		processData:false,
		//ContentType:undefined,
		success:function(res){
			selectedNode.name=$('#RenameTxt').val();
			zTree.updateNode(selectedNode);
		}
	});
});
//list subfiles
listSubfiles = function(tNode){
	$("#folder").data("folder_id",tNode.id).data("node_id",tNode.tId);
	getPath(tNode);
	
	var files = tNode.children;
	var file,folder = $("#folder ul");
	folder.html("");
	for(var i=0;i<files.length;i++){
		file = $(fileSpan);
		if(files[i].type == "dir"){
			file.addClass("folder");
		}
		file.find(".file_icon").
			addClass(files[i].type).
			data("file_id",files[i].id).
			data("node_id",files[i].tId).
			attr("title",files[i].name);
	
		file.find(".file_name").html(files[i].name);
		folder.append(file);
	}
}
//get the path of a tree node
getPath = function(tNode){
	var tempNode = tNode;
	var cPath = $("#children_path");
	cPath.html("");
	
	while(tempNode.getParentNode() != null){
		cPath.prepend($("<span/>").
				data("file_id",tempNode.id).
				data("node_id",tempNode.tId).
				html(tempNode.name));
		cPath.prepend(">");
		tempNode = tempNode.getParentNode();
	} 
}
//add file into zTree
addFile = function(data, parentNode){
	if(data.dir){
		data.isParent = true;
	}else{
		var fName = data.name;
		data.type=fName.substr(fName.lastIndexOf(".")+1,fName.length+1);
		data.icon = "../img/fileIcon/" + data.type+".gif";
	}
	
	var tarNode = zTree.getNodeByTId($("#folder").data("node_id")),
		file = $(fileSpan),
		newNode = zTree.addNodes(parentNode,data,true);
	if(data.dir){
		file.addClass("folder");
	}
	file.find(".file_icon").
		data("file_id",data.id).
		data("node_id",newNode[0].tId).
		attr("title",data.name);

	file.find(".file_name").html(data.name);
	$("#folder ul").append(file);
}
//fileSpan template
var fileSpan = 
		"<li class='file'>" +
			"<span class='file_icon'></span>" +
			"<span class='file_name'></span>" +
		"</li>";
</script>
</html>