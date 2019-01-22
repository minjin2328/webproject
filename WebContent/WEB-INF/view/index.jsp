<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내일로 숙소 웹 공간정보 서비스</title>
<link rel="stylesheet" href="css/ol.campsite.css"> 
<link rel="stylesheet" href="css/campsite_main.css">

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/ol-debug.js"></script>


<script>

/**
 * 통합 검색을 수행한다
 */
function frmAdvancedSearchSubmit()
{
	
	var paramRegion = new Array();
	var paramFacility = new Array();
	var paramTheme = new Array();
	var paramGrade= new Array();
	

	//1. JQuery 선택자를 이용하여 input 태그 중의 name 속성이 place 이면서, 선택된 엘레멘트(=태그)를 선택한다.  
	//2. 선택된 엘레멘트를 JQuery 반복문으로 순회한다.
	//3. 선택된 개별 엘레멘트의 값(value 속성값)을 paramPlace 배열 객체에 추가한다.

	$("input[name='region']:checked").each(function(i) {        
		paramRegion.push($(this).val());        
	});
	
	$("input[name='facility']:checked").each(function(i) {        
		paramFacility.push($(this).val());        
	});
	
	/**$("input[name='theme']:checked").each(function(i) {        
		paramTheme.push($(this).val());        
	}); */
	
	$("input[name='grade']:checked").each(function(i) {        
		paramGrade=$(this).val();        
	});
	
	//JavasSript Object Notation 형식의 POST 데이터로 가공
	var postdata = {'action':"SelectIntegration", 'paramRegion':paramRegion, 'paramFacility': paramFacility, 'paramTheme':paramTheme,'paramGrade':paramGrade};
	
	console.log(postdata);
	$.ajax({
	       url: "Tripsite.do",
	       cache: false,
	       dataType:"json",
	       method:"post",
	       data: postdata,	
	   }).done(function(data) {
		   var count = data.length;
		   var strTemp="";
		   for(var i =0;i<count;i++){
			   strTemp+=data[i];
			   strTemp+=(i<(count-1)?',':'');
		   }
		   console.log(strTemp);
		   
		   setGIDList(strTemp);
	   });
	return true;
}
</script>
</head>
<body>
	<div class="warp">
		<div id="header">
			<div class="topDecal"></div>
			<div id="campsiteLogo"></div>
			<h1>내일로 숙소 웹 공간정보서비스</h1>
		</div>
		<div id="contents">
			<div id="left">
				<div class="gnb">
					<ul>
					<a href="Tripsite.do"><li class="gnbicon icon1" onclick="gnbMenuSelect(1)"><span>숙소지도</span></li></a>
					<li class="gnbicon icon2" onclick="gnbMenuSelect(2)"><span>지역별</span></li>
					<li class="gnbicon icon3" onclick="gnbMenuSelect(3)"><span>등급별</span></li>
					<li class="gnbicon icon4" onclick="gnbMenuSelect(4)"><span>통합검색</span></li>
					</ul>
				</div>
				<div class="leftContents contents1">
					<h3>숙소 지도</h3>
					<p>좌측의 메뉴에서 지역별/등급별 검색을 하실 수 있습니다.</p>
				</div>
				<div class="leftContents contents2">
					<h3>지역별 검색</h3>
					<p>지역별 검색을 하실 수 있습니다.</p>
					<ul class="campsiteList">
						<li onclick="selectRegion('서울본부');">서울본부</li>
						<li onclick="selectRegion('수도동부');">수도동부</li>
						<li onclick="selectRegion('수도서부');">수도서부</li>
						<li onclick="selectRegion('충북본부');">충북본부</li>
						<li onclick="selectRegion('강원본부');">강원본부</li>
						<li onclick="selectRegion('전남본부');">전남본부</li>
						<li onclick="selectRegion('전북본부');">전북본부</li>
						<li onclick="selectRegion('경북본부');">경북본부</li>
					</ul>
				</div>
				<div class="leftContents contents3">
					<h3>등급별 검색</h3>
					<p>등급별 검색을 하실 수 있습니다.</p>
					<ul class="campsiteList">
						<li onclick="selectTheme('1등급');">1등급</li>
						<li onclick="selectTheme('2등급');">2등급</li>
						<li onclick="selectTheme('3등급');">3등급</li>
					</ul>
				</div>
				<div class="leftContents contents4">
					<h3>통합검색</h3>
					<p>통합 검색을 하실 수 있습니다.</p>
						<table class="campsiteTable" onSubmit="frmAdvancedSearchSubmit()">
							<tr>
								<th>지역</th>
								<td>
									<input type="checkbox" name="region" id="region1" value="서울"><label for="region1">서울</label>
									<input type="checkbox" name="region" id="region2" value="강원"><label for="region2">강원</label>
									<input type="checkbox" name="region" id="region3" value="전북"><label for="region3">전북</label>
									<input type="checkbox" name="region" id="region4" value="전남"><label for="region4">전남</label>
									<input type="checkbox" name="region" id="region5" value="경북"><label for="region5">경북</label>
									<input type="checkbox" name="region" id="region6" value="경남"><label for="region6">경남</label>
									<input type="checkbox" name="region" id="region7" value="경기"><label for="region7">경기</label>
								</td>
							</tr>
							<tr>
								<th>편의시설</th>
								<td>
									<input type="checkbox" name="facility" id="facility1" value="bed"><label for="facility1">침대</label>
									<input type="checkbox" name="facility" id="facility2" value="toilet"><label for="facility2">화장실</label>
									<input type="checkbox" name="facility" id="facility3" value="wifi"><label for="facility3">wifi</label><br>
								</td>
							</tr>
							<tr>
								<th>등급</th>
								<td>
									<input type="radio" name="grade" id="grade1" value="first"><label for="grade1">1등급</label>
									<input type="radio" name="grade" id="grade2" value="second"><label for="grade2">2등급</label>
									<input type="radio" name="grade" id="grade3" value="third"><label for="grade3">3등급</label><br>							
								</td>
							</tr>
						</table>
						<button style="float:right" onClick="javascript:frmAdvancedSearchSubmit()">검 색</button>

				</div>
			</div>
			<div id="mapArea">
				<div id="map"></div>
			</div>
		</div><!-- //contents -->
		<div id="footer">
			<span class="copyright">Copyright &copy; 2015 CampsiteWeb</span>
		</div><!-- //footer -->
	</div><!-- //wrap -->
	<div id="FeatureInfo">
		<h3>-</h3>
		<div class="btnClose"></div>
		<hr>
		<table>
			<tr>
				<th>가격</th>
				<td class="price"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td class="phone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td class="address"></td>
			</tr>
			<tr>
				<th>시설</th>
				<td class="facilities"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td class="grade"></td>
			</tr>
		</table>
	</div>
<script src="js/campsite.map.js"></script>
<script src="js/campsite.ui.js"></script>
<script>
function selectTheme(themename){
	var postdata={'action':'SelectTheme','theme':themename};
	$.ajax({
	       url: "Tripsite.do",
	       cache: false,
	       dataType:"json",
	       data: postdata,
	
	   }).done(function(data) {
		   var count = data.length;
		   var strTemp="";
		   for(var i =0;i<count;i++){
			   strTemp+=data[i];
			   strTemp+=(i<(count-1)?',':'');
		   }
		   console.log(strTemp);
		   
		   setGIDList(strTemp);
	   });
}

function selectRegion(regionname){
	var postdata={'action':'SelectRegion','region':regionname};
	$.ajax({
	       url: "Tripsite.do",
	       cache: false,
	       dataType:"json",
	       data: postdata,
	
	   }).done(function(data) {
		   var count = data.length;
		   var strTemp="";
		   for(var i =0;i<count;i++){
			   strTemp+=data[i];
			   strTemp+=(i<(count-1)?',':'');
		   }
		   console.log(strTemp);
		   
		   setGIDList(strTemp);
	   });
}
</script>
</body>
</html>