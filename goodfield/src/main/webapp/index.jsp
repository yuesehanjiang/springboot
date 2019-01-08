<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <script type="text/javascript"  src="/goodfield/jquery-3.3.1.js"></script>
</head>
<body>
    dfsfdsfsdfsdsdfsdfdfs
	<form    id="form1"   action="/goodfield/uploadImages" method="post" enctype="multipart/form-data">
		<input name="file" type="file"><br> 
		
		<input name="name"><br> <input type="submit"     class="btn2"  value="提交" />
        
	</form>



   <script type="text/javascript">
     $(".btn2").click(function() {
          var fd = new FormData($("#form1")[0]);
          $.ajax({
              url : "/goodfield/uploadImages",
              data : fd,
              type : "post",
              contentType : false,
            
              processData : false,
              
              xhr:function(){
            	  
            	  
            	  var xhr=$.ajaxSettings.xhr();
            	  
            	  xhr.upload.onprogress=function(event){
            		  
            		  console.log("已经上传",event.loaded);
            		  console.log("总计",event.total);
            	  }
            	  return xhr;
              },
            
             
              
              success : function(result) {
                   //来到邮箱填写页面
                   alert("上传成功:" + e);
              },
              error : function(e) {
            	   console.log(e);
                   alert("上传失败:" + e);
              }
          });

          return false;
     })
     </script>
     
</body>


   
</html>