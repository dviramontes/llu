
     function sC(current,e,cFName,cName,cPath,hOffset,wOffset)
     
        {
         if (document.layers) 
            {
             theString="<DIV CLASS='ttip'><b><big>"+cName+"</big></b><img align='middle' src='"+cPath+"/images/"+cFName+".gif'></DIV>";
             document.tooltip.document.write(theString);
             document.tooltip.document.close();
             document.tooltip.left=e.pageX+14;
             document.tooltip.top=e.pageY-38;
             document.tooltip.visibility="show";
            }
         else
           {
            if(document.getElementById)  
              {
               elm=document.getElementById("tooltip");
               elml=current;
               elm.innerHTML="<b><big>"+cName+"</big></b><img align='top' src='"+cPath+"/images/"+cFName+".gif'>";
               elm.style.height=elml.style.height;
               elm.style.top=parseInt(elml.offsetTop+elml.offsetHeight)-60+hOffset+"px";  //was 4
               //elm.style.top='100px';
               elm.style.left=parseInt(elml.offsetLeft+elml.offsetWidth)+62+wOffset+"px"; //was 8
               elm.style.position = "fixed";
               elm.style.visibility = "visible";
               //alert(elml.offsetTop+elml.offsetHeight);
              }
           }
        }
function hC(){

if (document.layers)  
   {
    document.tooltip.visibility="hidden";
   }
else
  {
   if(document.getElementById)  
     {
      elm.style.visibility="hidden";
     }
  } 
}