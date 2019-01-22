function FeatureInfoWndClose() {
    $("#FeatureInfo").hide();
    $("#FeatureInfo h3").text("-");
    $("#FeatureInfo td").text("-");
}


/**
 * 지도의 캠핑장 포인트를 클릭했을 때 피쳐 정보창 불러옴
 * @param feature
 */
function FeatureInfoLoad (feature) {
    var postdata = {
        'action': 'SelectCampsite',
        'trip_id': feature.get('trip_id')
    };
    $.ajax({
        url: "Tripsite.do",
        cache: false,
        data: postdata,

    }).done(function(data) {
        $("#FeatureInfo").html(data);
        $("#FeatureInfo").show();
    });

}



/**
 * 좌측 GNB 메뉴를 선택했을 때의 처리
 * @param gnbid
 */
function gnbMenuSelect(gnbid) {
    $(".leftContents.contents1").hide();
    $(".leftContents.contents2").hide();
    $(".leftContents.contents3").hide();
    $(".leftContents.contents4").hide();

    $("li.icon1").removeClass("selected");
    $("li.icon2").removeClass("selected");
    $("li.icon3").removeClass("selected");
    $("li.icon4").removeClass("selected");

    switch (gnbid) {
        case 1:
            $(".leftContents.contents1").show();
            $("li.icon1").addClass("selected");
            break;
        case 2:
            $(".leftContents.contents2").show();
            $("li.icon2").addClass("selected");
            break;
        case 3:
            $(".leftContents.contents3").show();
            $("li.icon3").addClass("selected");
            break;
        case 4:
            $(".leftContents.contents4").show();
            $("li.icon4").addClass("selected");
            break;
        default:
            $(".leftContents.contents1").show();
            $("li.icon1").addClass("selected");
            break;
    }
}
$(document).ready(function(){
	gnbMenuSelect(1); //페이지 로드시 1번 표시	
});
