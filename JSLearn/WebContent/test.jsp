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
		String filePath = application.getRealPath("/") + "FreqLow";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		int[] frequencies = new int[10000];
		Integer k = 0;
		Integer v = 0;
		//get the hashmap...
		while ((tuple = br.readLine()) != null) {
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequencies[k] = v;
		}
	%>

	<script type="text/javascript">
		
	<%-- assign JSP array to javascript array once for ever! --%>
		var frequencyMap = new Array(10000);
	<%for (int i = 0; i < 10000; i++) {%>
		frequencyMap[
	<%=i%>
		] = [
	<%=i / 100%>
		,
	<%=i % 100%>
		,
	<%=frequencies[i]%>
		];
	<%}%>
		var squeezedFrequencyMap = new Array(100);
	<%-- Squeeze the matrix to 10 * 10 --%>
		
	<%int frequencyCount = 0;%>
		
	<%for (int i = 0; i < 10000; i++) {%>
		
	<%frequencyCount += frequencies[i];%>
		
	<%if (i % 100 == 0) {%>
		
	<%int s = i / 100;%>
		squeezedFrequencyMap[
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
		var frequencyCount = 0;

		function showFrequenciesFunc(name) {
			//finally get the array......
			//pairs from 1 to 10000
			//2D map can show the data..
			//d3.select("body").append("p").transition().text(frequencyMap[key][0]);
			if (name == "low") {
				var svg = d3.select("body").append("svg").attr("width", 300)
						.attr("height", 300);

				svg.selectAll("circle").data(squeezedFrequencyMap).enter()
						.append("circle").attr("cx", function(d) {
							return d[0] * 20 + 10;
						}).attr("cy", function(d) {
							return d[1] * 20 + 10;
						}).attr("fill", function(d) {
							//return "hsl(200, " +  d[2] * 2  + "% ,50%)";
							var l = 100 - d[2] * 2;
							return "hsl(200, 10%, " + l + "%)";
							//return "hsl(200, 10%, 0%)";
						}).attr("r", 10);
			}

		}

		function showOneFrequenciesFunc() {
			//var svg = d3.select("body").append("svg").attr("width", 1000).attr("height", 1000);
			var svg = d3.select("body").selectAll("svg");

			var X = 20 * (frequencyCount / 100);
			var Y = 20 * (frequencyCount % 100);

			//svg.selectAll("circle").data(value).enter().append("circle")
			//	.attr("cx",X).attr("cy", Y).attr("r", 5).attr("class", "pumpkin");

			//Create SVG element

			svg.selectAll("circle").data(frequencyMap).enter().append("circle")
					.attr("cx", function(d) {
						return d[0];
					}).attr("cy", function(d) {
						return d[1];
					}).attr("r", 5);

			XX = XX + 10;
			YY = YY + 10;
			frequencyCount++;

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
		<button type="button" onclick="showFrequenciesFunc('low')">showFrequenciesLow</button>
	</p>
	<p>
		<button type="button" onclick="showFrequenciesFunc()">showFrequenciesMid</button>
	</p>
	<p>
		<button type="button" onclick="showFrequenciesFunc()">showFrequenciesHigh</button>
	</p>
	<p>
		<button type="button" onclick="showFrequenciesFunc()">showFrequenciesOrigin</button>
	</p>

	<!-- SVG testing -->

</body>
</html>