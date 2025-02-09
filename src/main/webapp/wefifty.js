async function ajaxRequest(method, query, postData = null) {
  return new Promise((resolve, reject) => {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState === 4) {
        if (this.status >= 200 && this.status < 300) {
          resolve(this.responseText);
        } else {
          reject(new Error(this.responseText));
        }
      }
    };
    xhttp.open(method, query);
    if (method === "POST" && postData) {
      xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    }
    xhttp.send(postData);
  });
}

function friendRequest(senderId, receiverId) {
	/*  
		https://sweetalert2.github.io/#icons
		Swal.fire parameter
		icon : "success" | "error" | "warning" | "info" | "question"
	*/
	
	/* action 실행 전 확인 팝업 */
	Swal.fire({
		title: "친구 정보 요청",
		html: "<p>해당 멤버가 요청을 수락하면 정보확인이 가능합니다.</p> <br> <p>500 포인트가 차감됩니다.</p>",
		icon: "question",
		showCancelButton: true,
		confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
		
		// 확인 클릭 시
		if (result.isConfirmed) {
			
			// controller Action 호출
			ajaxRequest (
				"POST", 
				"home?command=FriendRequest&senderId=" + senderId +"&receiverId=" + receiverId
			).then((succ) => {
				// 정상 응답 시
				Swal.fire("요청 완료!", "친구 요청이 성공적으로 전송되었습니다.", "success");
			}).catch((err) => {
				// 비정상 응답 시
				Swal.fire("요청 실패", err.message, "error");
			});
			
		}
	});
}

function loadView(viewName, viewScript) {
/*  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("home-mainView").innerHTML =
      this.responseText;
	  viewScript();
    }
  };
  xhttp.open("POST", "views/" +viewName+ ".jsp");
  xhttp.send();*/
  	const homeMainView = document.getElementById("home-mainView");
	ajaxRequest(
		"POST",
		"views/" + viewName + ".jsp"
	).then((succ) => {
		homeMainView.innerHTML = succ;
	  	viewScript();
	}).catch((err) => {
		console.log(err)
		homeMainView.innerHTML = `
		<div>
			<p>죄송합니다.<p/>
			<p>현재 서비스 이용이 불가합니다.<p/>
		</div>
		`	
	})

  
}

function profileScript() {
	console.log("profileScript");
}

function friendListScript() {
	console.log("friendListScript");
	
	const homeMainView = document.getElementById("home-mainView");
	
	// 서블릿 부르기
	ajaxRequest("POST", "home?command=GetFriendList")
	.then((succ) => {
		homeMainView.innerHTML = succ; // 성공일 시 페이지 교체
	}).catch((err) => {
		Swal.fire("요청 실패", err.message, "error");  // 실패일 시 팝업 띄우기
	})
}

function friendRequestListScript() {
	console.log("friendRequestListScript");
	
	const homeMainView = document.getElementById("home-mainView");
	
	// 서블릿 부르기
	ajaxRequest("POST", "home?command=GetFriendRequestList")
	.then((succ) => {
		homeMainView.innerHTML = succ; // 성공일 시 페이지 교체
	}).catch((err) => {
		Swal.fire("요청 실패", err.message, "error");  // 실패일 시 팝업 띄우기
	})
}

function recommendScript() {
	console.log("recommendScript loaded");
	
	// 캐러셀 생성
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
}

document.addEventListener("DOMContentLoaded", function() {
	console.log('page onLoad');
	
	// default view load
	loadView("recommend", recommendScript);
	
});

// 친구 목록 검색
function filterFriends() {
	let input = document.getElementById("searchInput").value.toLowerCase();
	let rows = document.querySelectorAll("#friendTable tbody tr");

	rows.forEach(row => {
		let name = row.querySelector("td:nth-child(2)").textContent.toLowerCase();

		if (name.includes(input)) {
			row.style.display = "";
		} else {
			row.style.display = "none";
		}
	});
}

// 친구 요청 수락 / 거절
function updateFriendRequest(decision, requestId) {
	// controller Action 호출
	ajaxRequest(
		"POST",
		"home?command=UpdateFriendRequest&decision=" + decision + "&requestId=" + requestId
	).then((succ) => {
		// 정상 응답 시
		if (decision == 'a') {
			Swal.fire("완료!", "친구 요청이 성공적으로 수락되었습니다.", "success");
		} else if (decision == 'r') {
			Swal.fire("완료!", "친구 요청이 성공적으로 거절되었습니다.", "success");
		}
		
		friendRequestListScript();  // 새로고침
	}).catch((err) => {
		// 비정상 응답 시
		Swal.fire("요청 실패", err.message, "error");
	});
}

// 포인트 충전
function pointChargingScript() {
	console.log("pointChargingScript");
	
	const homeMainView = document.getElementById("home-mainView");
	
	// 서블릿 부르기
	ajaxRequest("POST", "home?command=viewPointCharging")
	.then((succ) => {
		homeMainView.innerHTML = succ; // 성공일 시 페이지 교체
	}).catch((err) => {
		Swal.fire("요청 실패", err.message, "error");  // 실패일 시 팝업 띄우기
	})
}

// 포인트 충전 시 결제 진행
function submitPayment() {
    const amount = document.getElementById("amount").value;
    const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked');

	if (amount <= 0 || isNaN(amount)) {
		Swal.fire("오류", "충전 금액을 올바르게 입력하세요.", "error");
	    return false;
	}
				
    if (!paymentMethod) {
        Swal.fire("오류", "결제 수단을 선택하세요.", "error");
        return false;
    }

    const data = new URLSearchParams();
    data.append("amount", amount);
    data.append("paymentMethod", paymentMethod.value);

	ajaxRequest("POST", "home?command=payment", data.toString())
		.then((response) => {
	    	const result = JSON.parse(response);

	        if (result.success) {
	            // 결제 성공 후, 포인트 충전 AJAX 요청 실행
	            chargePoints(result.amount);
	        } else {
	        	Swal.fire("오류", result.message, "error");
	        }
	    })
	    .catch((err) => {
	    	Swal.fire("요청 실패", err.message, "error");
	    });

	    return false; // 기본 폼 제출 방지
}

// 결제후 포인트 충전 db 반영 요청
function chargePoints(amount) {
    const data = new URLSearchParams();
    data.append("amount", amount);

    ajaxRequest("POST", "home?command=pointCharging", data.toString())
        .then((response) => {
            const result = JSON.parse(response);
			
            if (result.success) {
				// 포인트 충전 완료 화면을 로드
				loadView("pointChargingResult", () => {}); // amount 값 전달
            } else {
                Swal.fire("오류", result.message, "error");
            }
        })
        .catch((err) => {
            Swal.fire("요청 실패", err.message, "error");
        });
}
