webpackJsonp([16],{"2x/o":function(t,a){},mvMB:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var s=e("vLgD");var i={data:function(){return{isLoading:!0,url:"",id:0}},mounted:function(){var t,a=this;(t={},Object(s.a)({method:"post",url:"/gym/appUrl/getAppUrl",data:t})).then(function(t){200===Number(t.code)?(a.url=t.data.url,t.data.id&&(a.id=t.data.id)):a.$message({message:t.message,type:"error"})}).finally(function(){a.isLoading=!1})},methods:{handleSubmit:function(){var t,a=this;this.isLoading=!0,(t={url:this.url,id:this.id},Object(s.a)({method:"post",url:"/gym/appUrl/saveAppUrl",data:t})).then(function(t){200===Number(t.code)?a.$message({message:"修改成功",type:"success"}):a.$message({message:t.message,type:"warning"})}).finally(function(){a.isLoading=!1})}}},n={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{directives:[{name:"loading",rawName:"v-loading",value:t.isLoading,expression:"isLoading"}],staticClass:"url-container"},[e("div",{staticClass:"url-form"},[e("div",{staticClass:"form-item"},[e("div",{staticClass:"form-label"},[t._v("URL")]),t._v(" "),e("div",{staticClass:"form-control"},[e("Input",{staticStyle:{width:"100%"},attrs:{placeholder:"为正常使用二维码，请输入app/小程序URL"},model:{value:t.url,callback:function(a){t.url=a},expression:"url"}})],1)]),t._v(" "),e("div",{staticClass:"form-item"},[e("div",{staticClass:"form-label"}),t._v(" "),e("div",{staticClass:"form-control"},[e("button",{staticClass:"confirm-button",on:{click:t.handleSubmit}},[t._v("确定")])])])])])},staticRenderFns:[]};var r=e("VU/8")(i,n,!1,function(t){e("2x/o")},"data-v-11efe382",null);a.default=r.exports}});
//# sourceMappingURL=16.588264dfb296b5125602.js.map