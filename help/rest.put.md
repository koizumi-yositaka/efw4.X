<H1>rest.put</H1>

The put function is established to call a restAPI with put method.
Its return is a JSON object.
<h2>Sample</h2>
<pre>
	var params={"nm":"customer name 1"};
	var ret = rest.put("http://localhost:8080/restSample/efwRestAPI/customer/u001",params);
	// {"id":"u001","nm":"customer name 1"}
</pre>

<h2>API</h2>

<table>
<tr><th>Calling</th><th>Returning</th></tr>
<tr><td>rest . put ( apiUrl , params )</td><td>null or JSON</td></tr>
</table>

<table>
<tr><th>Parameters</th><th>Type</th><th>Description</th></tr>
<tr><td>apiUrl</td><td>String</td><td>The url for the called rest api.</td></tr>
<tr><td>put</td><td>JSON Object</td><td>To send values to the rest api.</td></tr>
</table>
