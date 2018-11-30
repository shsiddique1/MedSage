<%@include file="taglib_includes.jsp"%>
<html>
<head>
<script type="text/javascript">
</script>
<title>medSage CSV Importing</title>
</head>
<body style="font-family: Arial; font-size: smaller;">
	<table bgcolor="lightblue" width="50%" align="center">
		<tr>
			<td align="center"><h2>medSage CSV Importing</h2></td>
		</tr>
		<tr>
		 <td align="center"><font color="#FF0000">${error}</font></td>
		</tr>
		<tr>
			<td align="center"><form:form id="formid" action="home.do"
					method="post" commandName="wccData">
					<div align="center">
						<table width="500" align="center">
							<tr>
								<td align="left"><h3>Orders Category</h3></td>
							</tr>
							<tr>
								<td><form:select path="orderId" items="${ordersList}"
										multiple="true"
										onchange="javascript:execute('getInvoices.do');"
										style="width: 500px; height:100px" /></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
</body>
</html>