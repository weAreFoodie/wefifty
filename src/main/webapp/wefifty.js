

function loadView(viewName) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("home-mainView").innerHTML =
      this.responseText;
    }
  };
  xhttp.open("POST", "views/" +viewName+ ".jsp");
  xhttp.send();
}



document.addEventListener("DOMContentLoaded", function() {
	console.log('page onLoad');
	loadView("recommend");
	
	// 친구 정보 요청 버튼 -> 팝업
	document.querySelectorAll(".add-member-btn").forEach(button => {
		const myId = null // cookie 학습 후 작성
		const friendId = button.getAttribute("data-userid");

		button.addEventListener("click", function() {
			Swal.fire({
				title: "친구 정보 요청",
				html: "<p>해당 멤버가 요청을 수락하면 정보확인이 가능합니다.</p> <br> <p>500 포인트가 차감됩니다.</p>",
				icon: "question",
				showCancelButton: true,
				confirmButtonText: "너 맞니?",
				cancelButtonText: "아 착각했네"
			}).then((result) => {
				if (result.isConfirmed) {
					try {
						Swal.fire("요청 완료!", "친구 요청이 성공적으로 전송되었습니다.", "success");
					} catch (e) {
						console.log(e);
						Swal.fire("요청 실패!", "잠시 뒤에 다시 시도해 주세요.", "error");
					}
				}

			});
		});
	});
	
	document.addEventListener("DOMContentLoaded", function() {
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
	});
});


