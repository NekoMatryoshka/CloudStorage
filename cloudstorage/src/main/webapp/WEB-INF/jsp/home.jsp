<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>

<title>Disk Home Page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="../js/jquery/2.0.0/jquery.min.js"></script>
<script src="../js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="../js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="../js/zTree/jquery.ztree.exedit-3.5.min.js"></script>
<link href="../css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" />
<link href="../css/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet" />

</head>

<body>
<div id="body">

<div id="header">
	<a id="logo" href="disk">Cloud Storage</a>
	<div id="user">
		Welcome, <span id="username">${user.username}</span>
		Size: <span id="size">${disk.size}</span> KB 
		Used: <span id="usedSize">${disk.usedSize}</span> KB 
		File Number: <span id="usedSize">${disk.fileNumber}</span>
	</div>
	<div id="toolBar">
		<span id="mkDir">New Folder</span>
		<span id="upload">Upload File</span>
	</div>
</div>

<div id="main">
	<div id="zTree">
		<ul id="fileTree" class="ztree">
		</ul>
	</div>
</div>

</div>
</body>

<script type="text/javascript">
	//ztree main initialization function
	$(document).ready(function(){
		zTree = $.fn.zTree.init($("#fileTree"), setting, zNodes);
		var root = zTree.getNodeByTId("fileTree_1");
		zTree.reAsyncChildNodes(root,"refresh",true);
		$("#root span").data("node_id","fileTree_1").data("file_id",${rootDirectoryID});
		root.isClick = true;
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
   				if(file.isDir){ //if is a folder, set type to dir
   					file.type="dir" 
   				}else{ //else set type to file type
   					var name = file.name;
   					file.type=name.substr(name.lastIndexOf(".")+1,name.length+1);
   				}
   				if(file.type != "dir"){
   					myFile.icon = "/img/fileIcon/" + myFile.type+".gif"; //set icon to gif path
   				}else {
   					myFile.isParent = true; //else set isParent to true
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

	
					
</script>
</html>









