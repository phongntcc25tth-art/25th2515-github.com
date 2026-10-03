<h1> App Ứng dụng Game Ghép Hình Nhớ (Memory Match) — Trò chơi rèn luyện trí nhớ trên Android 
</h1>
<h3>🎓 ĐỒ ÁN CUỐI KỲ MÔN LẬP TRÌNH THIẾT BỊ DI ĐỘNG (ANDROID)</h3>
<hr>

<h2>📌 THÔNG TIN ĐỒ ÁN</h2>
<ul>
  <li><b>Sinh viên thực hiện:</b>Nguyễn Tấn Phong </li>
  <li><b>Mã số sinh viên:</b> 25TH2515</li>
  <li><b>Lớp:</b> CC25CTH</li>
  <li><b>Giảng viên hướng dẫn:</b> Mai Cường Thọ</li>
  <li><b>Trường:</b> Đại học Nha Trang (NTU)</li>
  <li><b>Nền tảng phát triển:</b> Android Studio, Ngôn ngữ Java, Cơ sở dữ liệu SQLite</li>
</ul>
<h2>📖 CHƯƠNG 1: GIỚI THIỆU & MỤC TIÊU ĐỀ TÀI</h2>
</ul>
<p>Ứng dụng <b> "Game Ghép Hình Nhớ (Memory Match)" được xây dựng trên nền tảng hệ điều hành Android, hướng tới việc tạo ra một trò chơi giải trí đơn giản nhưng hiệu quả, giúp người dùng rèn luyện và cải thiện khả năng ghi nhớ thông qua việc lật thẻ và tìm các cặp hình giống nhau.</p>
<p>Trong cuộc sống hiện đại <b>, việc rèn luyện trí nhớ là vô cùng cần thiết ở mọi lứa tuổi. Trò chơi ra đời với giao diện thân thiện, hình ảnh sinh động, thao tác dễ sử dụng, phù hợp với mọi đối tượng từ trẻ em đến người lớn. Ứng dụng hoạt động hoàn toàn trên thiết bị di động, không yêu cầu kết nối mạng, đảm bảo người dùng có thể chơi bất kỳ lúc nào, bất kỳ đâu.</p>
<h3>Mục tiêu chính:</h3>
<li><b>Rèn luyện và cải thiện trí nhớ:</b> Thiết kế cơ chế chơi đơn giản — lật thẻ, ghi nhớ vị trí và tìm cặp giống nhau — giúp người dùng luyện tập khả năng ghi nhớ một cách tự nhiên và thú vị.</li>
<li><b>Quản lý trạng thái trò chơi:</b> Theo dõi chính xác số lượt chơi, số điểm đạt được, cập nhật ngay lập tức sau mỗi lật thẻ, tạo sự công bằng và khuyến khích người chơi cố gắng hơn.</li>
<li><b>Lưu trữ điểm cao:</b> Sử dụng cơ chế lưu trữ trên thiết bị, ghi lại điểm số cao nhất mà người dùng đạt được, tạo động lực cạnh tranh và cải thiện thành tích trong các lần chơi sau.</li>
<li><b>Giao diện trực quan, sinh động:</b> Sử dụng hình ảnh thân thuộc (trái cây, biểu tượng dễ thương), phối màu hài hòa, phân biệt rõ ràng mặt sau và mặt trước thẻ, tạo cảm g  iác dễ chịu, thu hút người chơi.</li>
<li><b>Hoạt động hoàn toàn ngoại tuyến:</b> Ứng dụng không yêu cầu kết nối internet, dữ liệu được lưu trữ cục bộ trên thiết bị, đảm bảo tính riêng tư và tiết kiệm tài nguyên mạng.</li>
</ul>
<hr>





<h2> CHƯƠNG 2: HÌNH ẢNH GIAO DIỆN & GIẢI THÍCH CHỨC NĂNG</h2>
<h3>1. 📱Màn hình chào & Biểu tượng ứng dụng, tên Memory Match, hướng dẫn chơi</h3>
<hr>
<table border="1" width="100%" cellpadding="8" style="border-collapse: collapse;">
  <thead>
 </i>
<img width="395" height="577" alt="image" src="https://github.com/user-attachments/assets/6e52de22-50d0-4eb3-a597-29f5b7d24256" />   

<li><b>Màn hình Bắt đầu: Hiển thị tên ứng dụng, hướng dẫn ngắn gọn cách chơi; khi nhấn nút PLAY NOW sẽ chuyển sang màn hình chơi chính, khởi tạo bộ thẻ mới, đặt lại điểm và số lượt về 0.</li>
<h3>2. Màn hình chơi & Khung Điểm, khung Lượt, lưới thẻ bài lật tìm cặp giống nhau</h3>  
</i>
<img width="286" height="507" alt="image" src="https://github.com/user-attachments/assets/a61c5a21-b2e4-4408-9aa9-016cbd9663c0" />

<li><b>Chơi & Tính điểm: Lật thẻ xem hình; khi mở 2 thẻ giống nhau → cộng điểm, khóa cố định; khi khác nhau → đóng lại; mỗi lần mở 2 thẻ đều tăng số lượt chơi; cập nhật liên tục khung Điểm và Lượt trên màn hình.</li>

<h3>3. Kết thúc & lựa chọn — Tự phát hiện khi hoàn thành tất cả cặp, hiển thị màn hình kết quả; cho phép chơi lại ván mới hoặc quay về trang chủ.</h3>
</i>
<img width="406" height="662" alt="image" src="https://github.com/user-attachments/assets/4a12ee9f-988c-4c0a-87d1-f65f9f5f450b" />

<li><b>Kết thúc & Điều hướng: Khi tìm hết tất cả cặp → tự động chuyển màn hình kết quả hiển thị tổng điểm và tổng lượt; nhấn CHƠI LẠI để xáo thẻ, đặt lại số liệu chơi tiếp; nhấn Trang chủ để quay về màn hình bắt đầu.</li>







BÁO CÁO KẾT THÚC MÔN LẬP TRÌNH THIẾT BỊ DI ĐỘNG 
https://docs.google.com/document/d/1laYqlIsFx20Q9RJsoi4Cmh1PSlzVon3mkbhbrLElLiI/edit?usp=sharing



