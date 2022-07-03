function openDialogue(url) {
	 var left = (screen.width/2)-(600/2);
	  var top = (screen.height/2)-(700/2);
	  window.open(url, "_blank", "scrollbars=yes,width=600,height=700", top='+top+', left='+left+');
}
function clearText(textcd, text_name) {
	document.getElementById(textcd).value="";
	document.getElementById(text_name).value="";
	
}