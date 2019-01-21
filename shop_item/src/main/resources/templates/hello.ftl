<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    hello, ${name}
<hr/>
<#if age < 18>
    weichengnain
    <#else>
    cheeeeeeeeeengnain
</#if>
<#list  names as n>
    ${n}<br>
</#list>
${time?date};

</body>
</html>