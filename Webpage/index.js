var http = require('http');
var fs = require('fs');
var express = require('express');
var myParser = require('body-parser');
var app = express();
var page = 0;
//2.
var server = http.createServer(function (req, resp) {
	displayPage(page, resp);

	console.dir(req.param);

  if (req.method == 'POST') {
 		console.log('POST');
		 var body = ''
    req.on('data', function(data) {
			body += data;
     	//console.log('Partial body: ' + body)
			var ends = body.split('|');
			var path;
			var writ;
			
			switch(ends[0]){
			case '/pursue':
				//complete action
				console.log("HERE I AM!");
				break;
			case '/Fgame':
				//complete action
				path = ends[1];
				writ = ends[2];
				writeAway(path, writ);
				break;
			case '/sSurvey':
				path = ends[1];
				writ = ends[2];
				writeAway(path, writ);
				break;
			case '/Sgame':
				path = ends[1];
				writ = ends[2];
				writeAway(path, writ);
				break;
			case '/Tgame':
				//complete action
				writeAway(body);
				break;
			case 'endItAll':
				//complete action
				break;
		}
    });
		//resp.end();
    
	}
});

server.listen(5050);

function writeAway(inputPath, inputData){

		var certainly = inputData;
		fs.open(inputPath, 'w', function(err, fd) {
			if (err) {
					throw 'could not open file: ' + err;
			}
			var buffer = new Buffer(certainly);
			fs.write(fd, buffer, 0, buffer.length, null, function(err) {
				if (err) throw 'error writing file: ' + err;
				fs.close(fd, function() {
						console.log('wrote the file successfully');
				});
			});
		});
	console.log("Got here");
}

function displayPage(pNum, respect){
	var path = "bigTogether.html";
	fs.readFile(path, function (error, pgResp) {
		if (error) {
			respect.writeHead(404);
			respect.write('Contents you are looking are Not Found');
		} else {
			respect.writeHead(200, { 'Content-Type': 'text/html' });
			respect.write(pgResp);
		}
						
		respect.end();
	});
}
 
console.log('Server Started listening on 5050');
