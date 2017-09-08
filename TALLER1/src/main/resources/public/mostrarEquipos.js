agregarEquipo();
function agregarEquipo() {

	console.log("retrive data");
	var dmJSON = "http://localhost:4567/equipos";
	$.getJSON( dmJSON, function(data) { 
	var items = [];
	console.log(data);
	
	$.each(data, function(key, val) {
		items.push("<tr>");
		items.push("<td>"+val.nombre+"</td>");
		items.push("<td>"+val.anoFundacion+"</td>");
		items.push("<td>"+val.numTitulos+"</td>");
		items.push("</tr>");
		
	});
	$("<tbody/>", {html: items.join("")}).appendTo("table");
	
});
}

