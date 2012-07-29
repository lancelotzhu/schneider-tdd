<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="buyStock.title"/></title>
    <meta name="heading" content="<fmt:message key='buyStock.heading'/>"/>
</head>

<body id="buyStock"/>

<div class="separator"></div>

<s:form name="buyStockForm" action="buy" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="tradeDTO.stockName"/>
        <s:hidden key="tradeDTO.accountBalance"/>
    </li>

    <li class="info">
        <fmt:message key="buyStock.message"/>
    </li>

   	<s:textfield key="tradeDTO.stockCode" cssClass="text large" required="true"/>
   	
    <li>
        <fieldset>
            <legend><fmt:message key="tradeDTO.stockName"/></legend>
	        <c:out value="${tradeDTO.stockName}" escapeXml="false"/>
        </fieldset>
    </li>   	
    
    <li>
        <fieldset>
            <legend><fmt:message key="tradeDTO.accountBalance"/></legend>
	        <c:out value="${tradeDTO.accountBalance}" escapeXml="false"/>
        </fieldset>
    </li>   	

    <s:textfield key="tradeDTO.requestedPrice" required="true" cssClass="text large"/>

    <s:textfield key="tradeDTO.quantity" required="true" cssClass="text large"/>

    <li class="buttonBar bottom">
        <s:submit key="button.refreshStockInfo" name="refresh" method="refresh" cssClass="button"/>
        <s:submit key="button.placeOrder" name="placeOrder" method="placeOrder" cssClass="button"/>
        <s:submit key="button.reset" name="reset" cssClass="button"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["buyStockForm"]);
</script>
