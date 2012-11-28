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
  box-shadow: 0 0 4px rgba(0,0,0,.2);
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

div.bar{
  display: inline-block;
  width: 20px;
  height: 75px;
  background-color: teal;
  margin-right: 2px;
}

.pumpkin{
	fill:yellow;
	stroke:orange;
	stroke-width:5;
}
</style>


<script type = "text/javascript">

	var p;
	var count = 0;
	var addedChart = 0;
	
	function todo(){
		var strongElem = document.getElementsByTagName("strong");
		strongElem[0].style.display= 'none';
		strongElem[0].style.border = "1px dashed red";
		alert("asdf");
	};
	
	function junk(){
		d3.selectAll("p").style("color", "blue");
		d3.select("body").style("background-color", "black");
	}
	
	function resizeFunc(){
		factor1 = [2,2,2,2,2];
		factor2 = [0.5,0.5,0.5,0.5,0.5];
	
		
		if(count == 0){
					
			d3.select("body").selectAll("div").data(factor1).attr("class", "bar")
			.transition().style("height", function(d){
				var barHeight = parseFloat(d3.select(this).style("height")) * d ;
		    	return barHeight + "px"
			}).style("width", function(d){
				var barWidth = parseFloat(d3.select(this).style("width")) * d ;
		    	return barWidth + "px"
			});
			
			count ++;
		}
		else if(count == 1){
			
			d3.select("body").selectAll("div").data(factor2).attr("class", "bar")
			.transition().style("height", function(d){
				var barHeight = parseFloat(d3.select(this).style("height")) * d ;
		    	return barHeight + "px"
			}).style("width", function(d){
				var barWidth = parseFloat(d3.select(this).style("width")) * d ;
		    	return barWidth + "px"
			});
			
			count --;
		}
		
		
	}
	//just append 
	function appendFunc(){
		d3.select("body").append("p").transition().text("New Paragraph!");
	}
	
	//data and bindin
	function dataBindFunc(){
		var dataset = [10,20,30,40,50];
		d3.select("body").selectAll("p")
	    .data(dataset)
	    .append("p")
	    .text("New paragraph!");
	}
	
	function transitionFunc(){
		if(count == 0){
			d3.select("body").transition().style("background-color", "white");
			d3.select("body").transition().style("background-color", "red");
			count ++;    
		}
		else if(count == 1){
			d3.select("body").transition().style("background-color", "white");
			count --;
		}
	}
	
	function addBar(){
		
		var dataset = [];
		for (var i = 0; i < 5; i ++){
			var newNumber = Math.random() * 30;
			dataset = dataset.concat(newNumber);
		}
		
		if(addedChart == 0){
			var p = d3.select("body").append("p");
			p.selectAll("div").data(dataset).enter().append("div").attr("class", "bar")
				.transition().style("height", function(d){
					var barHeight = d * 5;  //Scale up by factor of 5
			    	return barHeight + "px"
				});
			addedChart = 1;
		}
		
		else if(addedChart == 1){
			d3.select("body").selectAll("div").data(dataset).attr("class", "bar")
			.transition().style("height", function(d){
				var barHeight = d * 5;  //Scale up by factor of 5
		    	return barHeight + "px"
			});
		}
	}
	
	
	function circle(){
		var circle = svg.selectAll("circle");
	}
	
	
	
</script>
</head>


<body>


<script type = "text/javascript">
	var key = 0;
function showFrequenciesFunc(){
	
	<%
		String tuple;
		String filePath = application.getRealPath("/") + "FreqLow";
	  	BufferedReader br = new BufferedReader(new FileReader(filePath));
		int[] frequencies = new int[10000];
		Integer k = 0;
		Integer v = 0;
		//get the hashmap...
		while((tuple = br.readLine() ) != null){
			String[] tuples = tuple.split(" ");
			k = Integer.parseInt(tuples[0]);
			v = Integer.parseInt(tuples[1]);
			frequencies[k] = v;
		}
	%>
	
	key = key + 1;
	
	d3.select("body").append("p").transition().text("New Paragraph!");
	
	
}

</script>

<strong> I am very weak.</strong>

<p><button type="button" onclick="addBar()">addBar</button></p>
<p><button type="button" onclick="resizeFunc()">ResizeBar</button></p>
<!-- <button type="button" onclick="dataBindFunc()">dataBindFunc</button> -->
<p><button type="button" onclick="transitionFunc()">transision</button></p>
<p><button type="button" onclick="showFrequenciesFunc()">showFrequencies</button></p>

<!-- SVG testing -->
<svg width="500" height="100">

<circle cx="250" cy="30" r="25" class="pumpkin"/>
<text x = "300" y = "25" font-family="sans-serif"
 font-size="25" fill="gray"> My name is Shu. </text>

</svg>

</body>
</html>