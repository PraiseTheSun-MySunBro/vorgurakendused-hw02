webpackJsonp([1],{"1/oy":function(t,e){},"5T6F":function(t,e){},"5V6g":function(t,e){},"93nu":function(t,e){},"9M+g":function(t,e){},"9eyg":function(t,e){},Id91:function(t,e){},Jmt5:function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("7+uW"),n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("b-navbar",{attrs:{toggleable:"md",type:"dark",variant:"dark"}},[a("b-navbar-toggle",{attrs:{target:"nav_collapse"}}),t._v(" "),a("b-navbar-brand",{attrs:{to:{name:"Home"}}},[t._v("\n    Brand\n  ")]),t._v(" "),a("b-collapse",{attrs:{"is-nav":"",id:"nav_collapse"}},[a("b-navbar-nav",[a("b-nav-item",{attrs:{to:{name:"NewPost"}}},[t._v("Add post")])],1),t._v(" "),a("b-navbar-nav",{staticClass:"ml-auto"},[a("b-nav-item-dropdown",{attrs:{right:""}},[a("template",{slot:"button-content"},[a("em",[t._v("User")])]),t._v(" "),a("b-dropdown-item",{attrs:{href:"#"}},[t._v("Profile")]),t._v(" "),a("b-dropdown-item",{attrs:{to:{name:"Login"}},on:{click:t.logout}},[t._v("Signout")])],2)],1)],1)],1)},staticRenderFns:[]};var o={name:"App",components:{navigation:a("VU/8")({name:"Navigation",data:function(){return{}},methods:{logout:function(){this.$auth.logout()}}},n,!1,function(t){a("5V6g")},null,null).exports}},r={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("navigation"),this._v(" "),e("div",{staticClass:"components"},[e("router-view")],1)],1)},staticRenderFns:[]};var i=a("VU/8")(o,r,!1,function(t){a("p18I")},null,null).exports,c=a("e6fC"),l=a("mtWM"),u=a.n(l),d=a("Rf8U"),v=a.n(d),m=a("/ocq"),p={name:"Home",data:function(){return{posts:[]}},beforeRouteEnter:function(t,e,a){axios.get("/posts").then(function(t){a(function(e){console.log(t.data),e.posts=t.data})}).catch(function(t){console.error(t)})}},f={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},t._l(t.posts,function(e,s){return a("div",{key:s,staticClass:"row"},[a("div",{staticClass:"col-md-12"},[a("div",{staticClass:"card mb-4"},[a("div",{staticClass:"card-body"},[a("h2",{staticClass:"card-title"},[t._v(t._s(e.post.title))]),t._v(" "),a("p",{staticClass:"card-text"},[t._v(t._s(e.post.content))]),t._v(" "),a("button",{staticClass:"btn btn-danger"},[t._v("Delete")])]),t._v(" "),a("div",{staticClass:"card-footer text-muted"},[t._v("\n            Posted on "+t._s(e.post.createdAt)+" by "+t._s(e.account.username)+"\n          ")])])])])}))},staticRenderFns:[]};var _=a("VU/8")(p,f,!1,function(t){a("5T6F")},null,null).exports,h={data:function(){return{credentials:{username:"",password:""}}},methods:{onLogin:function(){this.$auth.login({data:{username:this.credentials.username,password:this.credentials.password},success:function(t){console.log(t)},error:function(t){console.error(t.response)},rememberMe:!0})}}},g={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"col-4 offset-4"},[a("div",{staticClass:"card"},[t._m(0),t._v(" "),a("div",{staticClass:"card-body"},[a("b-form",[a("div",{staticClass:"form-group"},[a("label",[t._v("Username")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.credentials.username,expression:"credentials.username"}],staticClass:"form-control",attrs:{type:"text",placeholder:"Enter username"},domProps:{value:t.credentials.username},on:{input:function(e){e.target.composing||t.$set(t.credentials,"username",e.target.value)}}})]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",[t._v("Password")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.credentials.password,expression:"credentials.password"}],staticClass:"form-control",attrs:{type:"password",placeholder:"Enter password"},domProps:{value:t.credentials.password},on:{input:function(e){e.target.composing||t.$set(t.credentials,"password",e.target.value)}}})])]),t._v(" "),a("small",[t._v("Don't have an account?")]),t._v(" "),a("router-link",{staticClass:"btn btn-primary mt-2",attrs:{to:"signup"}},[t._v("Sign up")])],1),t._v(" "),a("div",{staticClass:"card-footer"},[a("button",{staticClass:"btn btn-primary float-right",attrs:{type:"submit"},on:{click:function(e){return e.preventDefault(),t.onLogin(e)}}},[t._v("Log in")])])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"card-header"},[e("h1",[this._v("OurBlog")])])}]};var b=a("VU/8")(h,g,!1,function(t){a("r2/w")},"data-v-e69b5500",null).exports,C={data:function(){return{credentials:{username:"",password:"",email:""}}},methods:{onSignUp:function(){this.$auth.register({data:{username:this.credentials.username,password:this.credentials.password,email:this.credentials.email},success:function(t){console.log(t)},error:function(t){console.error(t.response)}})}}},w={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"col-4 offset-4"},[a("div",{staticClass:"card"},[t._m(0),t._v(" "),a("div",{staticClass:"card-body"},[a("form",[a("div",{staticClass:"form-group"},[a("label",[t._v("Username")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.credentials.username,expression:"credentials.username"}],staticClass:"form-control",attrs:{type:"text",placeholder:"Enter username"},domProps:{value:t.credentials.username},on:{input:function(e){e.target.composing||t.$set(t.credentials,"username",e.target.value)}}})]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",[t._v("Email")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.credentials.email,expression:"credentials.email"}],staticClass:"form-control",attrs:{type:"email",placeholder:"Enter email"},domProps:{value:t.credentials.email},on:{input:function(e){e.target.composing||t.$set(t.credentials,"email",e.target.value)}}})]),t._v(" "),a("div",{staticClass:"form-group"},[a("label",[t._v("Password")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.credentials.password,expression:"credentials.password"}],staticClass:"form-control",attrs:{type:"password",placeholder:"Enter password"},domProps:{value:t.credentials.password},on:{input:function(e){e.target.composing||t.$set(t.credentials,"password",e.target.value)}}})])])]),t._v(" "),a("div",{staticClass:"card-footer"},[a("router-link",{staticClass:"btn btn-primary",attrs:{to:"login"}},[t._v("Login")]),t._v(" "),a("button",{staticClass:"btn btn-primary float-right",on:{click:function(e){return e.preventDefault(),t.onSignUp(e)}}},[t._v("Create account")])],1)])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"card-header"},[e("h1",[this._v("OurBlog")])])}]};var y=a("VU/8")(C,w,!1,function(t){a("93nu")},"data-v-755cff88",null).exports,x={data:function(){return{postData:{title:"",content:""}}},methods:{addPost:function(){axios.put("/posts",{title:this.postData.title,content:this.postData.content}).then(function(t){console.log(t)}).catch(function(t){console.error(t.response)})}}},D={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"container"},[a("div",{staticClass:"row"},[a("div",{staticClass:"col-md-12"},[a("div",{staticClass:"card mb-4"},[t._m(0),t._v(" "),a("div",{staticClass:"card-body"},[a("div",{staticClass:"form-group"},[t._m(1),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.postData.title,expression:"postData.title"}],staticClass:"form-control col-4",attrs:{type:"text"},domProps:{value:t.postData.title},on:{input:function(e){e.target.composing||t.$set(t.postData,"title",e.target.value)}}})]),t._v(" "),a("div",{staticClass:"form-group"},[t._m(2),t._v(" "),a("textarea",{directives:[{name:"model",rawName:"v-model",value:t.postData.content,expression:"postData.content"}],staticClass:"form-control",attrs:{rows:"3"},domProps:{value:t.postData.content},on:{input:function(e){e.target.composing||t.$set(t.postData,"content",e.target.value)}}})])]),t._v(" "),a("div",{staticClass:"card-footer"},[a("button",{staticClass:"btn btn-primary float-right",on:{click:t.addPost}},[t._v("Publish")])])])])])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"card-header"},[e("h1",[this._v("New post")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("label",[e("h1",[this._v("Title:")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("label",[e("h4",[this._v("Content:")])])}]};var $=a("VU/8")(x,D,!1,function(t){a("9eyg")},"data-v-21d06b3f",null).exports;s.a.use(m.a);var E=new m.a({routes:[{path:"/",name:"Home",component:_,meta:{auth:!0}},{path:"/login",name:"Login",component:b,meta:{auth:!1}},{path:"/signup",name:"Signup",component:y,meta:{auth:!1}},{path:"/post/add",name:"NewPost",component:$,meta:{auth:!0}}]});a("Jmt5"),a("9M+g");s.a.config.productionTip=!1,s.a.config.devtools=!0,s.a.router=E,u.a.defaults.baseURL="http://localhost:9000/",s.a.use(c.a),s.a.use(v.a,u.a),window.axios=u.a,window.axios.defaults.headers.common={"X-Requested-With":"XMLHttpRequest","Content-Type":"application/json"},s.a.use(a("MLZB"),{auth:a("2T3s"),http:a("E/+Z"),router:a("LFDJ"),refreshData:{enabled:!1},fetchData:{enabled:!1},loginData:{url:"/auth/login",method:"POST",fetchUser:null},registerData:{url:"/auth/signup",method:"PUT",redirect:"/login"},authRedirect:{path:"/login"},logoutData:{redirect:"/login"}}),i.router=s.a.router,new s.a({el:"#app",router:E,components:{App:i},template:"<App/>"})},p18I:function(t,e){},"r2/w":function(t,e){},zj2Q:function(t,e){}},["NHnr"]);