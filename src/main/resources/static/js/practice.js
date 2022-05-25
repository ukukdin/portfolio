var searchForm = $("#searchForm");
$("#searchForm button").on("click", function(e) {
	if(!searchForm.find("option:selected").val()) {
		alert("검색 종류를 선택하세요");
		return false;
	}
	if(!searchForm.find("input[name='searchName']").val()) {
		alert("검색어를 입력하세요");
		return false;
	}
	searchForm.find("input[name='pageNum']").val("1");
	e.preventDefault();
	searchForm.submit();
});

$(".search-bar button").on("click", function(f) {
	f.preventDefault();
	let searchType = $(".search-bar select").val();
	let searchName = $(".search-bar input[name='searchName']").val();
	if(!searchType) {
		alert("검색 종류를 선택하세요.");
		return false;
	}
	if(!searchName) {
		alert("검색어를 입력해 주세요");
		return false;
	}
	moveForm.find("input[name='searchType']").val(searchType);
	moveForm.find("input[name='searchName']").val(searchName);
	moveForm.find("input[name='pageNum']").val(1);
	moveForm.submit();
});

let moveForm = $("#moveForm");
$(".move").on("click", function(e) {
	e.preventDefault();
	moveForm.append("<input type='hidden' name='movie_koficCd' value='" + $(this).attr("href") + "'>");
	moveForm.attr("action", "/search/getSearchList");
	moveForm.submit();
});

$(".pageInfo a").on("click", function(e) {
	e.preventDefault();
	moveForm.find("input[name='pageNum']").val($(this).attr("href"));
	moveForm.attr("action", "/search/getSearchList");
	moveForm.submit();	
});