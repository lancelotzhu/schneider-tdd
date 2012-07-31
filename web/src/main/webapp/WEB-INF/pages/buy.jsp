<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="buyStock.title"/></title>
    <meta name="heading" content="<fmt:message key='buyStock.heading'/>"/>
</head>

<body id="buyStock"/>

<div class="separator"></div>

<s:form name="buyStockForm" action="buy" method="post" validate="true">
    <li class="info">
        <fmt:message key="buyStock.message"/>
    </li>

   	<s:textfield key="tradeDTO.stockCode" cssClass="text large" required="true"/>
   	
    <s:textfield key="tradeDTO.price" required="true" cssClass="text large"/>

    <s:textfield key="tradeDTO.quantity" required="true" cssClass="text large"/>

    <li class="buttonBar bottom">
        <s:submit key="button.buy" name="buy" method="buy" cssClass="button"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["buyStockForm"]);
</script>
