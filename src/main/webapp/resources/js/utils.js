/**
 * Created by Drorz on 13.08.2014.
 */
function redirectToPath(urlPath) {
    window.location.pathname = urlPath;
}

function getTooltipId(id){
    return id+'Tooltip';
}

function showReportTooltip(id){
    var mainReport = document.getElementById(id);
    var tooltip = document.getElementById(getTooltipId(id));
    if(mainReport.innerHTML.trim().length > 0) {
        tooltip.innerHTML = mainReport.innerHTML;
        tooltip.style.display = "block";
    }
}


//TODO ToolTip сделать плавным, с отсрочкой и что бы не моргал или залипал если всплывает в с отступом
function hideReportTooltip(id) {
//    if(even.relatedTarget.id == id && even.target.id==getTooltipId(id)){
////        alert("0");
//        return;
//    }
////    alert("1");
    var tooltip = document.getElementById(getTooltipId(id));
    tooltip.innerHTML = "";
    tooltip.style.display = "none";
}
