<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포인트 충전</title>

    <!-- TailwindCSS & SweetAlert -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <style>
        body {
            background: url('https://source.unsplash.com/random/1920x1080?finance') no-repeat center center fixed;
            background-size: cover;
        }

		#home-mainView {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		}
		
        .container-box {
            max-width: 650px;
            width: 90%;
            padding: 40px;
            background: rgba(0, 0, 0, 0.4);
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            color: white;
            text-align: center;
        }

        .form-control {
            background: rgba(255, 255, 255, 0.15);
            color: white;
            border: none;
            padding: 12px;
            font-size: 18px;
            width: 100%;
            border-radius: 8px;
        }
        
        .form-control::placeholder {
            color: rgba(255, 255, 255, 0.7);
        }
        
        .form-control:focus {
            background: rgba(255, 255, 255, 0.25);
            color: white;
            outline: none;
        }

        .payment-options {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: center;
        }
        
        .payment-options label {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 48%;
            padding: 14px;
            border: 2px solid transparent;
            border-radius: 10px;
            cursor: pointer;
            background: rgba(255, 255, 255, 0.2);
            font-size: 18px;
            transition: all 0.3s ease-in-out;
            transform: scale(1);
        }

        .payment-options input[type="radio"] {
            display: none;
        }

        .payment-options label:hover {
            background: rgba(255, 255, 255, 0.4);
        }

        .payment-options input[type="radio"]:checked + label {
            background: #9DC08B;
            color: white;
            font-weight: bold;
            transform: scale(1.05); 
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            border: 2px solid white;
        }

        .payment-options label i {
            font-size: 22px;
            margin-right: 8px;
        }

        .btn-submit {
            width: 100%;
            padding: 15px;
            font-size: 20px;
            font-weight: bold;
            background: #A0C878; 
            border: none;
            color: white;
            border-radius: 8px;
            transition: all 0.3s ease-in-out;
        }

        .btn-submit:hover {
            background: #9DC08B; 
        }
    </style>
</head>
<body class="flex min-h-screen justify-center items-center">
	<div id="home-mainView">
	    <div class="container-box">
	        <h2 class="text-3xl font-bold mb-6">포인트 충전</h2>
	
	        <form id="paymentForm" onsubmit="return submitPayment();">
	            
	            <!-- 충전 금액 입력 -->
	            <div class="mb-4">
	                <label for="amount" class="text-lg mb-2 block">충전 금액 (원)</label>
	                <input type="number" class="form-control" id="amount" name="amount" required placeholder="금액 입력" min="1">
	            </div>
	            
				<br>
				
	            <!-- 결제 수단 선택 -->
	            <label class="text-lg mb-2 block">결제 수단</label>
	            <div class="payment-options">
	                <input type="radio" id="card" name="paymentMethod" value="card" required>
	                <label for="card"><i class="fas fa-credit-card"></i> 신용/체크카드</label>
	
	                <input type="radio" id="account" name="paymentMethod" value="account">
	                <label for="account"><i class="fas fa-university"></i> 계좌이체</label>
	
	                <input type="radio" id="kakaopay" name="paymentMethod" value="kakaopay">
	                <label for="kakaopay"><i class="fas fa-comment-dollar"></i> 카카오페이</label>
	
	                <input type="radio" id="naverpay" name="paymentMethod" value="naverpay">
	                <label for="naverpay"><i class="fas fa-credit-card"></i> 네이버페이</label>
	
	                <input type="radio" id="tosspay" name="paymentMethod" value="tosspay">
	                <label for="tosspay"><i class="fas fa-coins"></i> 토스페이</label>
	            </div>
	
	            <!-- 결제 버튼 -->
	            <button type="submit" class="btn btn-submit mt-4">결제하기</button>
	        </form>
	    </div>
	</div>
</body>
</html>
