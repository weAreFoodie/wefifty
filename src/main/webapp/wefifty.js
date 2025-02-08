
async function ajaxRequest(method, query) {
	/*
	method : "GET" | "POST"
	query : action 실행을 위한 쿼리
		- ex) home?command=FriendRequest&senderId=1&receiverId=10
	*/
	
	return new Promise((resolve, reject) => {
       const xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
           if (this.readyState == 4 && this.status === 200) {
               resolve(this.responseText);
           } else if (this.readyState == 4 && this.status === 400) {
				reject(new Error(this.responseText));
		   }
       };
       xhttp.open(method, query);
       xhttp.send();
   });
}

function friendRequest(senderId, receiverId) {
	/*  
		https://sweetalert2.github.io/#icons
		Swal.fire parameter
		icon : "success" | "error" | "warning" | "info" | "question"
	*/
	
	/* action 실행 전 확인 팝업*/
	Swal.fire({
		title: "친구 정보 요청",
		html: "<p>해당 멤버가 요청을 수락하면 정보확인이 가능합니다.</p> <br> <p>500 포인트가 차감됩니다.</p>",
		icon: "question",
		showCancelButton: true,
		confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
		
		/* 확인 클릭 시 */
		if (result.isConfirmed) {
			
			ajaxRequest (
				"POST", 
				"home?command=FriendRequest&senderId=" + senderId +"&receiverId=" + receiverId,
			).then((succ) => {
				/* 정상 응답 시*/
				Swal.fire("요청 완료!", "친구 요청이 성공적으로 전송되었습니다.", "success");
			}).catch((err) => {
				/* 비정상 응답 시 */
				Swal.fire("요청 실패", err.message, "error");
			});
				
			
		}
	});
}



function loadView(viewName, viewScript) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("home-mainView").innerHTML =
      this.responseText;
	  viewScript();
    }
  };
  xhttp.open("POST", "views/" +viewName+ ".jsp");
  xhttp.send();
}

function profileScript() {
	console.log("profileScript");
}

function friendListScript() {
	console.log("friendListScript");
}

function recommendScript() {
	console.log("recommendScript loaded");
	// 캐러셀
	new Swiper(".swiper-container", {
		loop: false,
		spaceBetween: 20,
		slidesPerView: 1,
		pagination: {
			el: ".swiper-pagination",
			clickable: true,
		},
		navigation: {
			nextEl: ".swiper-button-next",
			prevEl: ".swiper-button-prev",
		},
	});
	
	
	// 친구 정보 요청 버튼 -> 팝업
	document.querySelectorAll(".add-member-btn").forEach(button => {
		const myId = null // cookie 학습 후 작성
		const friendId = button.getAttribute("data-userid");

	});
}


document.addEventListener("DOMContentLoaded", function() {
	console.log('page onLoad');
	
	// default view
	loadView("recommend", recommendScript);
	
});


