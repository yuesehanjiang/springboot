webpackJsonp([4],{UZSd:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});a("jAzQ"),a("+BTi");var i=a("wOhx"),s=a.n(i),n=a("+lfT"),r=a("X1cf"),l=a("E4LH"),o={components:{Breadcrumb:r.a,Upload:s.a},data:function(){return console.log(this.$route.query.sex),{preview:"",form:{image:"",nickname:"",sex:3===Number(this.$route.query.sex)?"0":"1",signature:""},isSubmitting:!1,isGettingData:!1,checkFormData:"",disabled:!1}},computed:{sex:function(){var t=this.$route.query.sex;return 3===t?"female":2===t&&"male"}},watch:{"form.image":function(t){var e=this;if("string"!=typeof t){var a=new FileReader;a.readAsDataURL(this.form.image),a.addEventListener("load",function(){e.preview=a.result},{once:!0})}}},mounted:function(){var t=this;this.$route.params.id&&(this.isGettingData=!0,Object(n.g)({id:this.$route.params.id}).then(function(e){200===Number(e.code)?(t.isGettingData=!1,t.form.nickname=e.data.nickname,t.form.sex=e.data.sex,t.form.signature=e.data.signature,t.preview=e.data.image,t.checkFormData=JSON.stringify(t.form),t.disabled=!Number(e.data.type)):t.$message({message:e.message,type:"error",onClose:function(){t.$router.back()}})}))},methods:{beforeUpload:function(t){var e="3M",a=3145728;return-1===["image/jpg","image/jpeg","image/png","image/bmp"].indexOf(t.type)?(this.$message({message:"文件格式错误，请上传jpg,jpeg,png,bmp文件",type:"warning"}),!1):t.size>a?(this.$message({message:"文件太大，请上传"+e+"以下的文件",type:"warning"}),!1):(this.form.image=t,!1)},validate:function(){return JSON.stringify(this.form)!==this.checkFormData&&(this.preview?!l.a.isNullString(this.form.nickname)||(this.$message({message:"请输入用户名称",type:"warning"}),!1):(this.$message({message:"请上传照片",type:"warning"}),!1))},handleSubmit:function(){this.validate()&&this.postData()},getFormData:function(){var t=new FormData;return this.form.image&&t.append("file",this.form.image),t.append("nickname",this.form.nickname),t.append("sex",this.form.sex),t.append("signature",this.form.signature),t.append("gymAddr",this.$store.state.token),t.append("type",1),t.append("examine",1),t},postData:function(){var t=this;this.isSubmitting=!0;var e=this.getFormData(),a=void 0;this.$route.params.id?(e.append("id",this.$route.params.id),a=n.j):a=n.a,a(e).then(function(e){200===Number(e.code)?(t.$router.push({path:"/show/public"+(t.sex?Number(t.form.sex)?"/male":"/female":"")}),t.$message({message:e.message,type:"success"})):t.$message({message:e.message,type:"warning"})}).finally(function(){t.isSubmitting=!1})}}},d={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("PageBody",[a("Breadcrumb",{staticStyle:{"margin-bottom":"20px"}},[a("router-link",{attrs:{tag:"li",to:"/show/public"+(t.sex?"/"+t.sex:"")}},[t._v("会员秀")]),t._v(" "),a("li",[t._v("添加会员秀")])],1),t._v(" "),a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.isGettingData,expression:"isGettingData"}],staticClass:"detail-form"},[a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"},[t._v("上传照片")]),t._v(" "),a("div",{staticClass:"detail-control"},[a("Upload",{attrs:{action:"noAction","before-upload":t.beforeUpload}},[a("div",{staticClass:"picture-uploader"},[t.preview?a("img",{staticClass:"preview-img",attrs:{src:t.preview,alt:""}}):a("div",[a("i",{staticClass:"add-picture-icon"}),t._v(" "),a("p",{staticStyle:{color:"#bdbcbc","margin-top":"10px"}},[t._v("请上传3M内"),a("br"),t._v("大小的图片")])])])])],1)]),t._v(" "),a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"},[t._v("用户名称")]),t._v(" "),a("div",{staticClass:"detail-control"},[a("Input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入用户名称",maxlength:12,disabled:t.disabled},model:{value:t.form.nickname,callback:function(e){t.$set(t.form,"nickname",e)},expression:"form.nickname"}})],1)]),t._v(" "),a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"},[t._v("用户性别")]),t._v(" "),a("div",{staticClass:"detail-control"},[a("Select",{staticStyle:{width:"200px"},attrs:{disabled:t.disabled},model:{value:t.form.sex,callback:function(e){t.$set(t.form,"sex",e)},expression:"form.sex"}},[a("Option",{attrs:{value:"1"}},[t._v("男")]),t._v(" "),a("Option",{attrs:{value:"0"}},[t._v("女")])],1)],1)]),t._v(" "),a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"},[t._v("个性签名")]),t._v(" "),a("div",{staticClass:"detail-control"},[a("div",{staticClass:"detail-text-box"},[a("Input",{staticClass:"detail-text",attrs:{type:"textarea",placeholder:"请输入个性签名",disabled:t.disabled,maxlength:36},model:{value:t.form.signature,callback:function(e){t.$set(t.form,"signature",e)},expression:"form.signature"}}),t._v(" "),a("i",{staticClass:"detail-text-num"},[t._v(t._s(t.form.signature?36-t.form.signature.length:36))])],1)])]),t._v(" "),t.disabled?a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"}),t._v(" "),a("div",{staticClass:"detail-control"},[a("p",[t._v("（非本账号上传，不能修改编辑）")])])]):t._e(),t._v(" "),t.disabled?t._e():a("div",{staticClass:"detail-item"},[a("div",{staticClass:"detail-label"}),t._v(" "),a("div",{staticClass:"detail-control"},[a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.isSubmitting,expression:"isSubmitting"}],staticStyle:{display:"inline-block"}},[a("button",{staticClass:"gym-btn border-btn",on:{click:function(){t.$router.back()}}},[t._v("取消")]),t._v(" "),a("button",{staticClass:"gym-btn primary-btn confirm-btn",class:{"disable-confirm":JSON.stringify(t.form)===t.checkFormData},on:{click:t.handleSubmit}},[t._v("确定\n          ")])])])])])],1)},staticRenderFns:[]};var c=a("VU/8")(o,d,!1,function(t){a("XFNb")},"data-v-542084cc",null);e.default=c.exports},XFNb:function(t,e){}});
//# sourceMappingURL=4.328ac2ec5cd62f5cc1ce.js.map