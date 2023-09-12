function initCookie() {
	if ($.cookie('menu') == undefined) {
		$.cookie('menu', 'news/newsline');
		$.cookie('menuBack', 'news/newsline');
	}

	if ($.cookie('header') == undefined) {
		$.cookie('header', 'header');
	}

	if ($.cookie('main') == undefined) {
		$.cookie('main', 'news/newsCarousel');
		$.cookie('mainBack', 'news/newsCarousel');
	}

	if ($.cookie('locale') == undefined) {
		$.cookie('locale', 'en');
	}

}

function setCookie(name, url) {
	if ($.cookie(name) != url) {
		$.cookie(name + 'Back', $.cookie(name));
		$.cookie(name, url);
	}
}

function login() {
	setCookie('main', 'news/newsCarousel')
	get('#menu', 'news/newsline', setCookie('menu', 'news/newsline'));
	get('#header', 'header', setCookie('header', 'header'));
}

function updateAll() {
	get('#main', $.cookie('main'));
	get('#menu', $.cookie('menu'));
	get('#header', $.cookie('header'));
}



function get(element, url, func) {
	$.ajax({
		url: url,
		headers: {
			'Accept-Language': $.cookie("locale")
		},
		type: "GET",
		statusCode: {
			200: function(data) {
				$(element).html(data);
				if (func != undefined) {
					func();
				}
			},
			418: function(data) {
				$("#error").html(data.responseText);
			},
		}
	});
}

function multipart(form, element, url) {
	var form = $(form)[0];
	var data = new FormData(form);

	$.ajax({
		type: "POST",
		headers: {
			'Accept-Language': $.cookie("locale"),
			'X-CSRF-TOKEN': $("#csrf").val()
		},
		enctype: 'multipart/form-data',
		url: url,
		data: data,
		processData: false,
		contentType: false,
		cache: false,
		statusCode: {
			200: function(data) {
				$(element).html(data);
			},
			418: function(data) {
				$("#error").html(data.responseText);
			},
		}
	});
}

function post(form, element, url, func) {
	$.ajax({
		url: url,
		headers: {
			'Accept-Language': $.cookie("locale"),
			'X-CSRF-TOKEN': $("#csrf").val()
		},
		data: $(form).serialize(),
		type: "POST",
		statusCode: {
			200: function(data) {
				$(element).html(data);
				if (func != undefined) {
					func();
				}
			},
			418: function(data) {
				$("#error").html(data.responseText);
			},
		}
	});
}

function postNoUpdate(form, url) {
	$.ajax({
		url: url,
		headers: {
			'Accept-Language': $.cookie("locale"),
			'X-CSRF-TOKEN': $("#csrf").val(),
		},
		data: $(form).serialize(),
		type: "POST",
		statusCode: {
			418: function(data) {
				$("#error").html(data.responseText);
			},
		}
	});
}

initCookie();

$(document).ready(function() {
	updateAll();
});