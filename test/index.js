const request = require('request');
const fs = require('fs');

request.get('http://localhost:8080/api/email', (err, res, body) => {
	console.log('error:', err);
	console.log('statusCode:', res && res.statusCode);
	console.log('body:', body);
});

let email = {
	title: "Test sending email",
	message: "Hi, this is a test",
	recipients: [
		"bruno@spam4.me",
		"bruno2@spam4.me"
	],
	attachments: [
		{ filename: "kitty.png", data: base64Encode('./kitty.png') }
	]
};

let options = {
	uri: 'http://localhost:8080/api/email',
	method: 'POST',
	json: email
};

request(options, (err, res, body) => {
	console.log('error:', err);
	console.log('statusCode:', res && res.statusCode);
	console.log('body:', body);
});

// function to encode file data to base64 encoded string
function base64Encode(path) {
	// read binary data
	let file = fs.readFileSync(path);
	// convert binary data to base64 encoded string
	return new Buffer(file).toString('base64');
}
