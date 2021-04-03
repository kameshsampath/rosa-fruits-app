(this["webpackJsonpfruits-app"]=this["webpackJsonpfruits-app"]||[]).push([[0],{169:function(e,t,a){},276:function(e,t,a){},280:function(e,t,a){},281:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),i=a(32),c=a.n(i),l=(a(168),a(169),a(307)),s=a(158),o=a(297),u=a(298),d=a(299),f=a(303),j=a(7),b=a(98),h=a(301),p=a(292),m=a(306),O=a(293),x=a(294),v=a(295),g=a(296),y="ADD_FRUIT_ACTION",F="DELETE_FRUIT_ACTION",L="FRUITS_LOADED",E="FRUITS_LOADED_FAILED",S=[{value:"please choose",label:"Please Choose as season",disabled:!1},{value:"spring",label:"Spring",disabled:!1},{value:"summer",label:"Summer",disabled:!1},{value:"fall",label:"Fall",disabled:!1},{value:"winter",label:"Winter",disabled:!1},{value:"any",label:"Any",disabled:!1}],C=a(5),A=function(e){var t=e.dispatch,a=n.useState(""),r=Object(j.a)(a,2),i=r[0],c=r[1],l=n.useState(""),s=Object(j.a)(l,2),o=s[0],u=s[1],d=n.useState(),F=Object(j.a)(d,2),L=F[0],E=F[1],A=n.useState(!1),I=Object(j.a)(A,2),T=I[0],k=I[1],D=n.useState("You must fill out all required fields before you can proceed."),w=Object(j.a)(D,2),R=w[0],_=w[1],N=function(){k(!1),c(""),u(""),E((function(e){return!e}))};return Object(C.jsxs)(C.Fragment,{children:[Object(C.jsx)(b.a,{variant:"primary",onClick:N,children:"Add Fruits"}),Object(C.jsx)(h.a,{title:"Add Fruits",variant:h.b.medium,isOpen:L,onClose:N,actions:[Object(C.jsx)(b.a,{variant:"primary",onClick:function(){console.log(i,o);var e={name:i,season:o};if(i&&o){var a={method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(e)};fetch("".concat("/api","/fruit"),a).then((function(e){console.log(e),t({type:y,payload:{cells:[{title:i},{title:o},{title:"delete",fruitname:o}]}}),k((function(e){return!e})),E((function(e){return!e})),c(""),u("")})).catch((function(e){console.log(e),k((function(e){return!e})),_("".concat(e.status," - ").concat(e.errorText))}))}else k((function(e){return!e}))},children:"Confirm"},"confirm"),Object(C.jsx)(b.a,{variant:"link",onClick:N,children:"Cancel"},"cancel")],children:Object(C.jsxs)(p.a,{children:[Object(C.jsx)(m.a,{label:"Name",isRequired:!0,fieldId:"simple-form-fruit",children:Object(C.jsx)(O.a,{isRequired:!0,id:"simple-form-fruit",type:"text",placeholder:"Enter Fruit Name",name:"simple-form-fruit",value:i,"aria-label":"Fruit Name",onChange:function(e){return c(e)}})}),Object(C.jsx)(m.a,{label:"Season",isRequired:!0,fieldId:"simple-form-season",children:Object(C.jsx)(x.a,{isRequired:!0,id:"simple-form-season",placeholder:"Enter Fruit Season",name:"simple-form-season",value:o,"aria-label":"Fruit Season",onChange:function(e){return"please choose"!==e?u(e):k((function(e){return!e}))},children:S.map((function(e,t){return Object(C.jsx)(v.a,{isDisabled:e.disabled,value:e.value,label:e.label},t)}))})}),T&&Object(C.jsx)(g.a,{children:Object(C.jsx)(f.a,{variant:"danger",title:R,"aria-live":"polite",isInline:!0})})]})})]})},I=a(117),T=a.n(I),k=a(152),D=a(6),w=function(e,t){switch(t.type){case L:return{fruitsList:[].concat(Object(D.a)(e.fruitsList),Object(D.a)(t.payload)),loaded:!0};case E:return{fruitsList:Object(D.a)(e.fruitsList),loaded:!0,loadError:t.payload.loadError};case y:return{fruitsList:[].concat(Object(D.a)(e.fruitsList),[t.payload]),loaded:!0};case F:return{fruitsList:e.fruitsList.length?e.fruitsList.filter((function(e){var a,n;return(null===(a=e.cells)||void 0===a||null===(n=a[0])||void 0===n?void 0:n.title)!==t.payload.name})):[],loaded:!0};default:return e}},R=r.a.createContext({}),_=function(e){var t=r.a.useReducer(w,{fruitsList:[],loaded:!1}),a=Object(j.a)(t,2),n=a[0],i=a[1],c=function(e){var t=S.find((function(t){return t.value===e}));return null===t||void 0===t?void 0:t.label};r.a.useEffect((function(){fetch("".concat("/api","/fruits")).then(function(){var e=Object(k.a)(T.a.mark((function e(t){var a,n,r;return T.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.json();case 2:a=e.sent,t.ok?(n=a.map((function(e){return{cells:[{title:e.name},{title:c(e.season)},{title:"delete",fruitname:e.name}]}})),i({type:L,payload:n})):(r={},500===a.status?(r.loaded=!0,r.loadError="Some Error occurred fetching the fruits"):(r.loaded=!0,r.loadError="Error Code:".concat(a.status,", Error: ").concat(a.message)),i({type:E,payload:r}));case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()).catch((function(e){console.log("Error Retrieving Fruits: %s",e)}))}),[]);var l={state:n,dispatch:i};return Object(C.jsx)(R.Provider,{value:l,children:e.children})},N=a(300),P=a(305),q=a(302),U=[{title:"Name"},{title:"Season"},{title:"Action"}],J=function(e){var t=e.loaded,a=e.fruitsList,n=e.dispatch;return Object(C.jsx)(C.Fragment,{children:t?Object(C.jsx)(C.Fragment,{children:Object(C.jsxs)(N.a,{"aria-label":"Automated pagination table",cells:U,rows:a,rowWrapper:function(e){return Object(C.jsx)("tr",{children:e.row.cells.map((function(e,t){return Object(C.jsx)("td",{"data-label":U[t].title,children:"delete"===e.title?Object(C.jsx)("button",{onClick:function(t){return function(e,t){console.log(e,t),t.fruitname&&fetch("".concat("/api","/fruit/").concat(t.fruitname),{method:"DELETE"}).then((function(e){console.log(e),n({type:F,payload:{name:t.fruitname}})})).catch((function(e){console.log(e)}))}(t,e)},children:"Delete"}):e.title},e.title)}))})},children:[Object(C.jsx)(P.a,{}),Object(C.jsx)(q.a,{})]})}):Object(C.jsx)("h1",{children:"Loading...."})})},B=(a(276),function(){var e=r.a.useContext(R),t=e.state,a=t.fruitsList,n=t.loaded,i=t.loadError,c=e.dispatch;return i?Object(C.jsx)(f.a,{style:{marginTop:0},variant:"danger",isInline:!0,title:i}):n?Object(C.jsxs)(C.Fragment,{children:[Object(C.jsx)(A,{dispatch:c}),Object(C.jsx)(J,{loaded:n,fruitsList:a,dispatch:c})]}):Object(C.jsx)("h1",{children:"Loading...."})});var M=function(){var e=Object(C.jsx)(l.a,{style:{background:"#000",color:"#fff"},logo:Object(C.jsx)("h1",{children:"Fruits App"}),logoProps:{href:"https://github.com/kameshsampath/rosa-fruits-app",title:"Quarkus::Fruits API",target:"_blank"},children:" Fruits App "});return Object(C.jsx)(C.Fragment,{children:Object(C.jsx)(s.a,{header:e,isManagedSidebar:!0,children:Object(C.jsx)(o.a,{variant:o.b.light,children:Object(C.jsxs)(u.a,{children:[Object(C.jsx)(d.a,{component:"h1",children:"Show Fruits"}),Object(C.jsx)(d.a,{component:"p",children:"List the Fruits and season they are available in, can Add or remove fruits as well."}),Object(C.jsx)(_,{children:Object(C.jsx)(B,{})})]})})})})},W=function(e){e&&e instanceof Function&&a.e(3).then(a.bind(null,308)).then((function(t){var a=t.getCLS,n=t.getFID,r=t.getFCP,i=t.getLCP,c=t.getTTFB;a(e),n(e),r(e),i(e),c(e)}))};a(280);c.a.render(Object(C.jsx)(r.a.StrictMode,{children:Object(C.jsx)(M,{})}),document.getElementById("root")),W()}},[[281,1,2]]]);
//# sourceMappingURL=main.6967f33e.chunk.js.map