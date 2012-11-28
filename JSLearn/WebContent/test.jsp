<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.RandomAccessFile"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.TreeMap"%>


<html>
<head>
<title>Hey man</title>

<script src="http://d3js.org/d3.v2.js"></script>

<style type='text/css'>
.chart {
	background: #fff;
	border: solid 1px #ddd;
	box-shadow: 0 0 4px rgba(0, 0, 0, .2);
	font: 10px sans-serif;
	height: 180px;
	position: relative;
	width: 720px;
}

.chart svg {
	border-left: solid 2px #ddd;
	left: 360px;
	position: absolute;
	top: 0;
}

.chart pre {
	font: 12px monospace;
	height: 60px;
	left: 10px;
	position: absolute;
	top: 0;
	width: 340px;
}

.chart circle.little {
	fill: #aaa;
	stroke: #666;
	stroke-width: 1.5px;
}

.chart button {
	left: 275px;
	position: absolute;
	top: 145px;
	width: 80px;
}

.chart .data rect {
	fill: #eee;
	stroke: #ccc;
}

div.bar {
	display: inline-block;
	width: 20px;
	height: 75px;
	background-color: teal;
	margin-right: 2px;
}

.pumpkin {
	fill: yellow;
	stroke: orange;
	stroke-width: 5;
}
</style>


<script type="text/javascript">
	var p;
	var count = 0;
	var addedChart = 0;
	var key = 0;

	function todo() {
		var strongElem = document.getElementsByTagName("strong");
		strongElem[0].style.display = 'none';
		strongElem[0].style.border = "1px dashed red";
		alert("asdf");
	};

	function junk() {
		d3.selectAll("p").style("color", "blue");
		d3.select("body").style("background-color", "black");
	}

	function resizeFunc() {
		factor1 = [ 2, 2, 2, 2, 2 ];
		factor2 = [ 0.5, 0.5, 0.5, 0.5, 0.5 ];

		if (count == 0) {

			d3.select("body").selectAll("div").data(factor1).attr("class",
					"bar").transition().style(
					"height",
					function(d) {
						var barHeight = parseFloat(d3.select(this).style(
								"height"))
								* d;
						return barHeight + "px"
					}).style("width", function(d) {
				var barWidth = parseFloat(d3.select(this).style("width")) * d;
				return barWidth + "px"
			});

			count++;
		} else if (count == 1) {

			d3.select("body").selectAll("div").data(factor2).attr("class",
					"bar").transition().style(
					"height",
					function(d) {
						var barHeight = parseFloat(d3.select(this).style(
								"height"))
								* d;
						return barHeight + "px"
					}).style("width", function(d) {
				var barWidth = parseFloat(d3.select(this).style("width")) * d;
				return barWidth + "px"
			});

			count--;
		}

	}
	//just append 
	function appendFunc() {
		d3.select("body").append("p").transition().text("New Paragraph!");
	}

	//data and bindin
	function dataBindFunc() {
		var dataset = [ 10, 20, 30, 40, 50 ];
		d3.select("body").selectAll("p").data(dataset).append("p").text(
				"New paragraph!");
	}

	function transitionFunc() {
		if (count == 0) {
			d3.select("body").transition().style("background-color", "white");
			d3.select("body").transition().style("background-color", "red");
			count++;
		} else if (count == 1) {
			d3.select("body").transition().style("background-color", "white");
			count--;
		}
	}

	function addBar() {

		var dataset = [];
		for ( var i = 0; i < 5; i++) {
			var newNumber = Math.random() * 30;
			dataset = dataset.concat(newNumber);
		}

		if (addedChart == 0) {
			var p = d3.select("body").append("p");
			p.selectAll("div").data(dataset).enter().append("div").attr(
					"class", "bar").transition().style("height", function(d) {
				var barHeight = d * 5; //Scale up by factor of 5
				return barHeight + "px"
			});
			addedChart = 1;
		}

		else if (addedChart == 1) {
			d3.select("body").selectAll("div").data(dataset).attr("class",
					"bar").transition().style("height", function(d) {
				var barHeight = d * 5; //Scale up by factor of 5
				return barHeight + "px"
			});
		}
	}

	function circle() {
		var circle = svg.selectAll("circle");
	}
</script>
</head>


<body>

	<%
		String tuple;
		Integer k = 0;
		Integer v = 0;
		//low	
		String filePathL = application.getRealPath("FreqLow");
		BufferedReader brL = new BufferedReader(new FileReader(filePathL));
		int[] frequenciesLow = new int[10000];
		
		//get the hashmap...
		while ((tuple = brL.readLine()) != null) {
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequenciesLow[k] = v;
		}
		
		//mid
		String filePathM = application.getRealPath("FreqMid");
		BufferedReader brM = new BufferedReader(new FileReader(filePathM));
		int[] frequenciesMid = new int[10000];
		//get the hashmap...
		while ((tuple = brM.readLine()) != null) {
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequenciesMid[k] = v;
		}
		//high
		String filePathH = application.getRealPath("FreqHigh");
		BufferedReader brH = new BufferedReader(new FileReader(filePathH));
		int[] frequenciesHigh = new int[10000];
		//get the hashmap...
		while ((tuple = brH.readLine()) != null) {
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequenciesHigh[k] = v;
		}
		
		//original
		String filePathO = application.getRealPath("FreqO");
		BufferedReader brO = new BufferedReader(new FileReader(filePathO));
		int[] frequenciesOigh = new int[10000];
		//get the hashmap...
		while ((tuple = brO.readLine()) != null) {
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequenciesOigh[k] = v;
		}
	%>

	<script type="text/javascript">
		
	<%-- assign JSP array to javascript array once for ever! --%>
	var squeezedFrequencyMapLow = new Array(100);
	<%-- Squeeze the matrix to 10 * 10 --%>
	<%int frequencyCount = 0;%>
	<%for (int i = 0; i < 10000; i++) {%>
	<%frequencyCount += frequenciesLow[i];%>
	<%if ( (i+1) % 100 == 0) {%>
	<%int s = i / 100;%>
		squeezedFrequencyMapLow[
	<%=s%>
		] = [
	<%=(int) s / 10%>
		,
	<%=(int) s % 10%>
		,
	<%=frequencyCount%>
		];
	<%frequencyCount = 0;%>
	<%}%>
	<%}%>

	var squeezedFrequencyMapMid = new Array(100);
	<%-- Squeeze the matrix to 10 * 10 --%>
	<%frequencyCount = 0;%>
	<%for (int i = 0; i < 10000; i++) {%>
	<%frequencyCount += frequenciesMid[i];%>
	<%if ( (i+1) % 100 == 0) {%>
	<%int s = i / 100;%>
		squeezedFrequencyMapMid[
	<%=s%>
		] = [
	<%=(int) s / 10%>
		,
	<%=(int) s % 10%>
		,
	<%=frequencyCount%>
		];
	<%frequencyCount = 0;%>
	<%}%>
	<%}%>
	
	var squeezedFrequencyMapHigh = new Array(100);
	<%-- Squeeze the matrix to 10 * 10 --%>
	<%frequencyCount = 0;%>
	<%for (int i = 0; i < 10000; i++) {%>
	<%frequencyCount += frequenciesHigh[i];%>
	<%if ( (i+1) % 100 == 0) {%>
	<%int s = i / 100;%>
		squeezedFrequencyMapHigh[
	<%=s%>
		] = [
	<%=(int) s / 10%>
		,
	<%=(int) s % 10%>
		,
	<%=frequencyCount%>
		];
	<%frequencyCount = 0;%>
	<%}%>
	<%}%>
	
	var squeezedFrequencyMapOigh = new Array(100);
	<%-- Squeeze the matrix to 10 * 10 --%>
	<%frequencyCount = 0;%>
	<%for (int i = 0; i < 10000; i++) {%>
	<%frequencyCount += frequenciesOigh[i];%>
	<%if ( (i+1) % 100 == 0) {%>
	<%int s = i / 100;%>
		squeezedFrequencyMapOigh[
	<%=s%>
		] = [
	<%=(int) s / 10%>
		,
	<%=(int) s % 10%>
		,
	<%=frequencyCount%>
		];
	<%frequencyCount = 0;%>
	<%}%>
	<%}%>
		
		function animate(){
			d3.select(this).transition().
			duration(1000).attr("r",10).transision().delay(1000).attr("r",20);
		}
		function showfrequenciesFunc(name) {
			//finally get the array......
			//pairs from 1 to 10000
			//2D map can show the data..
			//d3.select("body").append("p").transition().text(frequencyMap[key][0]);
			
			if (name == "low") {
				var svg = d3.select("body").append("svg").attr("width", 300)
						.attr("height", 300);
				var title = "LOW ACCURACY";	
				svg.selectAll("text").data(title).enter().append("text")
				.text("LOW ACCURACY").attr("x", 100).attr("y", 30).attr("font-family", "Verdana").attr("text-anchor", "middle")
				.attr("font-size",20).attr("fill","Orange");
				
				svg.selectAll("circle").data(squeezedFrequencyMapLow).enter()
						.append("circle").attr("cx", function(d) {
							return d[0] * 20 + 10;
						}).attr("cy", function(d) {
							return d[1] * 20 + 50;
						}).attr("fill", function(d) {
							//return "hsl(200, " +  d[2] * 2  + "% ,50%)";
							var l = (100 - (d[2] / 1.5));
							return "hsl(0, 10%, " + l + "%)";
							//return "hsl(200, 10%, 0%)";
						}).attr("r", 10)
						.on("mouseover", function(){d3.select(this).style("fill", "Orange");})
						.on("mouseout", function(){
							if(d3.select(this).attr("r") != 10){
								d3.select(this).transition().delay(0).duration(1000).attr("r",10)
								.each("end",function(){
									d3.select(this).style("fill", function(d){
										var l = (100 - (d[2] / 1.5));
										return "hsl(0, 10%, " + l + "%)";
										});
								});			
							}
							else{
								d3.select(this).style("fill", function(d){
									var l = (100 - (d[2] / 1.5));
									return "hsl(0, 10%, " + l + "%)";
									});
							}
						})
						.on("mousedown", function(){
							//try rander again? so that on the top layer
							d3.select(this).style("fill", "Orange");
							d3.select(this).transition().delay(0).duration(1000).attr("r",50);
						});
			}
			
			if(name == 'mid'){
				var svg = d3.select("body").append("svg").attr("width", 300)
				.attr("height", 300);
				var title = "MID ACCURACY";
				svg.selectAll("text").data(title).enter().append("text")
					.text("MID ACCURACY").attr("x", 100).attr("y", 30).attr("font-family", "Verdana").attr("text-anchor", "middle")
					.attr("font-size",20).attr("fill","Orange");
				
				svg.selectAll("circle").data(squeezedFrequencyMapMid).enter()
				.append("circle").attr("cx", function(d) {
					return d[0] * 20 + 10;
				}).attr("cy", function(d) {
					return d[1] * 20 + 50;
				}).attr("fill", function(d) {
					//return "hsl(200, " +  d[2] * 2  + "% ,50%)";
					var l =  (100 - (d[2] / 1.5));
					return "hsl(0, 10%, " + l + "%)";
					//return "hsl(200, 10%, 0%)";
				}).attr("r", 10);
				
			}
			
			if(name == 'high'){
				var svg = d3.select("body").append("svg").attr("width", 300)
				.attr("height", 300);
				var title = "HIGH ACCURACY";	
				svg.selectAll("text").data(title).enter().append("text")
				.text("HIGH ACCURACY").attr("x", 100).attr("y", 30).attr("font-family", "Verdana").attr("text-anchor", "middle")
				.attr("font-size",20).attr("fill","Orange");
				svg.selectAll("circle").data(squeezedFrequencyMapHigh).enter()
				.append("circle").attr("cx", function(d) {
					return d[0] * 20 + 10;
				}).attr("cy", function(d) {
					return d[1] * 20 + 50;
				}).attr("fill", function(d) {
					//return "hsl(200, " +  d[2] * 2  + "% ,50%)";
					var l = (100 - (d[2] / 3));
					return "hsl(0, 10%, " + l + "%)";
					//return "hsl(200, 10%, 0%)";
				}).attr("r", 10);
				
			}
			
			if(name == 'origin'){
				var svg = d3.select("body").append("svg").attr("width", 300)
				.attr("height", 300);
				var title = "DATASET";	
				
				svg.selectAll("text").data(title).enter().append("text")
				.text("DATASET").attr("x", 100).attr("y", 30).attr("font-family", "Verdana").attr("text-anchor", "middle")
				.attr("font-size",20).attr("fill","Orange");
				
				svg.selectAll("DATASET").data(squeezedFrequencyMapOigh).enter()
				.append("circle").attr("cx", function(d) {
					return d[0] * 20 + 10;
				}).attr("cy", function(d) {
					return d[1] * 20 + 50;
				}).attr("fill", function(d) {
					//return "hsl(200, " +  d[2] * 2  + "% ,50%)";
					var l = (100 - d[2] / 100);
					return "hsl(0, 10%, " + l + "%)";
					//return "hsl(200, 10%, 0%)";
				}).attr("r", 10);
				
				
			}

		}

	</script>


	<p>
		<button type="button" onclick="addBar()">addBar</button>
	</p>
	<p>
		<button type="button" onclick="resizeFunc()">ResizeBar</button>
	</p>
	<!-- <button type="button" onclick="dataBindFunc()">dataBindFunc</button> -->
	<p>
		<button type="button" onclick="transitionFunc()">transition</button>
	</p>
	<p>
		<button type="button" onclick="showfrequenciesFunc('low')">showfrequenciesLow</button>
	</p>
	<p>
		<button type="button" onclick="showfrequenciesFunc('mid')">showfrequenciesMid</button>
	</p>
	<p>
		<button type="button" onclick="showfrequenciesFunc('high')">showfrequenciesHigh</button>
	</p>
	<p>
		<button type="button" onclick="showfrequenciesFunc('origin')">showfrequenciesOrigin</button>
	</p>
	

</body>
</html>