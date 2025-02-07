<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 | WEFIFTY</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-black">

    <!-- Navigation (Fixed at the top) -->
    <nav class="w-full px-10 py-4 flex justify-between border-b text-sm fixed top-0 left-0 bg-white shadow-md z-10 h-16">
        <div class="font-bold text-lg">WEFIFTY</div>
        <div class="font-bold text-sm">잊고 있던 친구들을 찾아보세요</div>
    </nav>

    <!-- Signup Form -->
    <div class="flex justify-center items-center min-h-screen mt-24">
        <div class="w-full max-w-2xl bg-white p-10 shadow-lg">
            <h2 class="text-3xl font-semibold mb-4">계정 만들기</h2>
            <p class="text-gray-600 mb-6">WEFIFTY 가입 후 잊고 있던 친구를 찾아보세요!</p>

            <div>
                <label class="block font-semibold">이메일*</label>
                <input type="email" class="w-full border p-2 rounded-md required-input">
            </div>

            <div>
                <label class="block font-semibold">비밀번호*</label>
                <input type="password" class="w-full border p-2 rounded-md required-input">
            </div>

            <div>
                <label class="block font-semibold">비밀번호 확인*</label>
                <input type="password" class="w-full border p-2 rounded-md required-input">
            </div>

            <div>
                <label class="block font-semibold">이름*</label>
                <input type="text" class="w-full border p-2 rounded-md required-input">
            </div>

            <!-- 성별 선택 -->
            <div>
                <label class="block font-semibold">성별*</label>
                <div class="flex space-x-4">
                    <label class="flex items-center">
                        <input type="radio" name="gender" class="mr-2 required-input"> 남성
                    </label>
                    <label class="flex items-center">
                        <input type="radio" name="gender" class="mr-2 required-input"> 여성
                    </label>
                </div>
            </div>

            <!-- 출생년월일 -->
            <div>
                <label class="block font-semibold">출생년월일*</label>
                <div class="flex space-x-2">
                    <select class="w-1/3 border p-2 rounded-md required-input">
                        <option selected disabled>출생 연도</option>
                        <script>
                            for (let year = 1940; year <= 2025; year++) {
                                document.write(`<option>${year}</option>`);
                            }
                        </script>
                    </select>

                    <select class="w-1/3 border p-2 rounded-md required-input">
                        <option selected disabled>월</option>
                        <script>
                            for (let month = 1; month <= 12; month++) {
                                document.write(`<option>${month}</option>`);
                            }
                        </script>
                    </select>

                    <select class="w-1/3 border p-2 rounded-md required-input">
                        <option selected disabled>일</option>
                        <script>
                            for (let day = 1; day <= 31; day++) {
                                document.write(`<option>${day}</option>`);
                            }
                        </script>
                    </select>
                </div>
            </div>

            <div>
                <label class="block font-semibold">휴대전화번호*</label>
                <div class="flex space-x-2">
                    <select class="border p-2 rounded-md required-input">
                        <option>대한민국 (+82)</option>
                    </select>
                    <input type="text" class="flex-1 border p-2 rounded-md required-input">
                </div>
            </div>

            <!-- 학력 정보 -->
            <div>
                <label class="block font-semibold">초등학교</label>
                <div class="flex space-x-2">
                    <input type="text" class="w-2/3 border p-2 rounded-md school-input" placeholder="학교명 입력">
                    <select class="w-1/3 border p-2 rounded-md school-year bg-gray-200" disabled>
                        <option selected>졸업년도</option>
                        <script>
                            for (let year = 1960; year <= 2025; year++) {
                                document.write(`<option>${year}</option>`);
                            }
                        </script>
                    </select>
                </div>
            </div>

            <div>
                <label class="block font-semibold">중학교</label>
                <div class="flex space-x-2">
                    <input type="text" class="w-2/3 border p-2 rounded-md school-input" placeholder="학교명 입력">
                    <select class="w-1/3 border p-2 rounded-md school-year bg-gray-200" disabled>
                        <option selected>졸업년도</option>
                        <script>
                            for (let year = 1960; year <= 2010; year++) {
                                document.write(`<option>${year}</option>`);
                            }
                        </script>
                    </select>
                </div>
            </div>

            <div>
                <label class="block font-semibold">고등학교</label>
                <div class="flex space-x-2">
                    <input type="text" class="w-2/3 border p-2 rounded-md school-input" placeholder="학교명 입력">
                    <select class="w-1/3 border p-2 rounded-md school-year bg-gray-200" disabled>
                        <option selected>졸업년도</option>
                        <script>
                            for (let year = 1960; year <= 2010; year++) {
                                document.write(`<option>${year}</option>`);
                            }
                        </script>
                    </select>
                </div>
            </div>

            <div>
                <label class="block font-semibold">대학교</label>
                <div class="flex space-x-2">
                    <input type="text" class="w-2/3 border p-2 rounded-md school-input" placeholder="학교명 입력">
                    <select class="w-1/3 border p-2 rounded-md school-year bg-gray-200" disabled>
                        <option selected>졸업년도</option>
                        <script>
                            for (let year = 1960; year <= 2010; year++) {
                                document.write(`<option>${year}</option>`);
                            }
                        </script>
                    </select>
                </div>
            </div>

                <!-- 필수 체크박스 -->
                <div class="space-y-2 mt-4">
                    <div>
                        <input type="checkbox" id="terms_all" class="required-checkbox">
                        <label for="terms_all" class="text-sm font-semibold">개인정보 수집 및 이용 동의 (필수)</label>
                    </div>

                    <div>
                        <input type="checkbox" id="terms_policy" class="required-checkbox">
                        <label for="terms_policy" class="text-sm">개인정보 처리위탁 동의 (필수)</label>
                    </div>

                    <div>
                        <input type="checkbox" id="terms_service" class="required-checkbox">
                        <label for="terms_service" class="text-sm">서비스 이용약관 동의 (필수)</label>
                    </div>
                </div>

                <!-- Buttons -->
                <div class="flex space-x-4 mt-6">
                    <button type="reset" class="w-1/2 border border-gray-400 py-2 rounded-md">취소</button>
                    <button type="submit" id="submit-btn" class="w-1/2 bg-gray-300 py-2 rounded-md cursor-not-allowed" disabled>가입하기</button>
                </div>
            </form>
        </div>
    </div>

    <!-- JavaScript for validation -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const form = document.getElementById("signup-form");
            const submitBtn = document.getElementById("submit-btn");
            const requiredInputs = document.querySelectorAll(".required-input");
            const requiredCheckboxes = document.querySelectorAll(".required-checkbox");

            function validateForm() {
                let allFieldsFilled = true;

                // 필수 입력 필드 확인
                requiredInputs.forEach(input => {
                    if (input.type === "radio") {
                        const radioGroup = document.querySelectorAll(`input[name="${input.name}"]:checked`);
                        if (radioGroup.length === 0) {
                            allFieldsFilled = false;
                        }
                    } else if (!input.value) {
                        allFieldsFilled = false;
                    }
                });

                // 필수 체크박스 확인
                requiredCheckboxes.forEach(checkbox => {
                    if (!checkbox.checked) {
                        allFieldsFilled = false;
                    }
                });

                // "다음" 버튼 활성화 / 비활성화
                if (allFieldsFilled) {
                    submitBtn.disabled = false;
                    submitBtn.classList.remove("bg-gray-300", "cursor-not-allowed");
                    submitBtn.classList.add("bg-blue-500", "hover:bg-blue-600", "text-white");
                } else {
                    submitBtn.disabled = true;
                    submitBtn.classList.remove("bg-blue-500", "hover:bg-blue-600", "text-white");
                    submitBtn.classList.add("bg-gray-300", "cursor-not-allowed");
                }
            }

            // 입력 값 변경 시 유효성 검사
            requiredInputs.forEach(input => input.addEventListener("input", validateForm));
            requiredCheckboxes.forEach(checkbox => checkbox.addEventListener("change", validateForm));
        });
        
        document.addEventListener("DOMContentLoaded", function () {
            const schoolInputs = document.querySelectorAll(".school-input");
            const schoolYears = document.querySelectorAll(".school-year");

            // 학교 입력 시 졸업년도 활성화
            schoolInputs.forEach((input, index) => {
                input.addEventListener("input", function () {
                    if (input.value.trim() !== "") {
                        schoolYears[index].disabled = false;
                        schoolYears[index].classList.remove("bg-gray-200");
                        schoolYears[index].classList.add("bg-white");
                    } else {
                        schoolYears[index].disabled = true;
                        schoolYears[index].classList.remove("bg-white");
                        schoolYears[index].classList.add("bg-gray-200");
                    }
                });
            });
        });
    </script>

</body>
</html>