<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="gis.vo.CampsiteInfo"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${CampsiteInfo.name ne null}">
<h3>${CampsiteInfo.name}</h3>
		<div class="btnClose" onclick="FeatureInfoWndClose();"></div>
		<hr>
		<table>
			<tr>
				<th>주소</th>
				<td class="address">${CampsiteInfo.address}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td class="phone">${CampsiteInfo.phone}</td>
			</tr>
			<tr>
				<th>가격</th>
				<td class="price">${CampsiteInfo.price}</td>
			</tr>
			<tr>
				<th>시설</th>
				<td class="facilities">
					<c:if test="${CampsiteInfo.bed eq 1}">침대, </c:if>
					<c:if test="${CampsiteInfo.toilet eq 1}">화장실, </c:if>
					<c:if test="${CampsiteInfo.wifi eq 1}">wifi, </c:if>
				</td>
			</tr>
			<tr>
				<th>등급</th>
				<td class="grade">${CampsiteInfo.grade}</td>
			</tr>
		</table>
	</c:if>