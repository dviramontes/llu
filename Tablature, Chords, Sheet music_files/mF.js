function verifyIE(){if(navigator.appName.toUpperCase()=='MICROSOFT INTERNET EXPLORER')return true;else return false;}
var iO = document.getElementById("sndFrame");
function pc(sL){
if(false){ verifyIE()==true
	
	if (document.all){
		// this doesn't work in XP ie 6?
		document.all.bgSnd.src = sL;
		setTimeout("n()",99999);
		}}
else{
//reset the iFrame
var frDoc = iO.contentDocument || iO.contentWindow.document; 
frDoc.documentElement.innerHTML = "";
iO.src = sL;
//setTimeout("n()",99999);
}}

function n(){iO.src="about:blank";}