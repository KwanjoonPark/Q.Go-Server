-- 카테고리 데이터
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://www.jejunews.com/news/photo/202212/2198897_222733_08.jpg', '제주국제공항');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://api.cdn.visitjeju.net/photomng/imgpath/202110/20/32ec3ee6-fad9-440d-95ea-628ff6453a48.jpg', '협재해수욕장');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://upload.wikimedia.org/wikipedia/commons/2/24/Chunjeyeon_Waterfall_Jeju_Island-_%EC%B2%9C%EC%A0%9C%EC%97%B0%ED%8F%AD%ED%8F%AC.jpg', '천제연폭포');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://api.cdn.visitjeju.net/photomng/imgpath/202409/20/c8df320a-80df-47d9-a541-bf86631e5d51.png', '성산일출봉');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://pix10.agoda.net/hotelImages/29537497/-1/a686e5e602b55400a2733c2742678724.jpg?ca=25&ce=0&s=414x232', '시리우스호텔');

-- 샘플 위치 데이터
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('공항 엔젤리너스', 33.506231, 126.493940, 1, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EC%97%94%EC%A0%A4%EB%A6%AC%EB%84%88%EC%8A%A4.jpg', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('공항 파리바게트', 33.504898, 126.492234, 1, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EC%A0%9C%EC%A3%BC%EA%B3%B5%ED%95%AD+%ED%8C%8C%EB%B0%94.jpg', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('Jeju Airport', 33.50607, 126.49321, 1, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/JejuAirport.jpg', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('공항입구', 33.505419, 126.496185, 1, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B3%B5%ED%95%AD%EC%9E%85%EA%B5%AC.png', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('공항 주차장', 33.503320, 126.495957, 1, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B3%B5%ED%95%AD%EC%A3%BC%EC%B0%A8%EC%9E%A5.png', 0, 0);

INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('바다가 보이는 마을', 33.462244, 126.936288, 4, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%94%EB%8B%A4%EA%B0%80+%EB%B3%B4%EC%9D%B4%EB%8A%94+%EB%A7%88%EC%9D%84.png', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('바다가 보이는 돌해변', 33.458396, 126.942564, 4, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%94%EB%8B%A4%EA%B0%80+%EB%B3%B4%EC%9D%B4%EB%8A%94+%EB%8F%8C%ED%95%B4%EB%B3%80.png', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('사건의 수평선', 33.4592586997, 126.9396761997, 4, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EC%82%AC%EA%B1%B4%EC%9D%98+%EC%88%98%ED%8F%89%EC%84%A0.jpg', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('일출봉 산책로', 33.505419, 126.496185, 4, 'https://lh3.googleusercontent.com/gps-cs-s/AC9h4nq_I3glDZyGcHZc6K3jpjAWKh30eqMDnePAJOwEYPuBXa2f3da6TxWPx3BFzF6nj3wgJFSKWHcCKrWJ_6u3Ne2T6lRfB0ezyy-QdVXzOOlDIz4qHBnuTM6Kox2jCmJkn5rlaWiE=w408-h306-k-no', 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산 전망대', 33.459842, 126.939519, 4, 'https://locquest-bucket.s3.ap-northeast-2.amazonaws.com/%EC%9D%BC%EC%B6%9C%EB%B4%89+%EC%A0%84%EB%A7%9D%EB%8C%80.png', 0, 0);

-- 샘플 사용자 데이터
INSERT INTO user_tbl (user_id, nickname, profile_image, registered_date) VALUES (100001, '한정진', 'https://example.com/profile1.jpg', NOW());
INSERT INTO user_tbl (user_id, nickname, profile_image, registered_date) VALUES (100002, '노건호', 'https://example.com/profile2.jpg', NOW());
INSERT INTO user_tbl (user_id, nickname, profile_image, registered_date) VALUES (100003, '크킄슨', 'https://example.com/profile3.jpg', NOW());

-- 더 많은 위치 데이터 (협재해수욕장)
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재해수욕장 입구', 33.395200, 126.239800, 2, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재 카페거리', 33.394500, 126.240200, 2, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재 주차장', 33.393800, 126.239600, 2, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재 비양도 전망대', 33.395800, 126.240800, 2, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재 해변 산책로', 33.394000, 126.239000, 2, NULL, 0, 0);

-- 더 많은 위치 데이터 (천제연폭포)
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('천제연폭포 입구', 33.247800, 126.417500, 3, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('선임교', 33.247500, 126.417200, 3, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('천제연 1폭포', 33.247300, 126.417100, 3, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('천제연 2폭포', 33.247000, 126.417000, 3, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('천제연 3폭포', 33.246800, 126.416900, 3, NULL, 0, 0);

-- 더 많은 위치 데이터 (성산일출봉)
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산일출봉 입구', 33.458800, 126.940200, 4, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산일출봉 정상', 33.459200, 126.940800, 4, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산일출봉 전망대', 33.459000, 126.940600, 4, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산 해안도로', 33.458500, 126.939800, 4, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('성산포항', 33.457800, 126.939200, 4, NULL, 0, 0);

-- 더 많은 위치 데이터 (시리우스호텔)
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('시리우스호텔 로비', 33.499200, 126.531800, 5, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('시리우스호텔 수영장', 33.499000, 126.531600, 5, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('시리우스호텔 레스토랑', 33.499100, 126.531700, 5, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('시리우스호텔 주차장', 33.498800, 126.531500, 5, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('시리우스호텔 카페', 33.499300, 126.531900, 5, NULL, 0, 0);

-- 샘플 게임 데이터 (Explorer 모드)
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Explorer', '2025-08-21 10:00:00', '2025-08-21 11:30:00', TRUE, 5, '2025-08-21', 1, 2);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Explorer', '2025-08-20 14:00:00', '2025-08-20 15:45:00', TRUE, 4, '2025-08-20', 2, 1);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Explorer', '2025-08-19 09:30:00', '2025-08-19 11:00:00', FALSE, 2, '2025-08-19', 3, 3);

INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Explorer', '2025-08-21 13:00:00', '2025-08-21 14:20:00', TRUE, 5, '2025-08-21', 1, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Explorer', '2025-08-20 11:00:00', '2025-08-20 12:30:00', TRUE, 3, '2025-08-20', 4, 1);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Explorer', '2025-08-18 16:00:00', '2025-08-18 17:45:00', TRUE, 4, '2025-08-18', 5, 2);

INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Explorer', '2025-08-21 08:00:00', '2025-08-21 09:45:00', TRUE, 5, '2025-08-21', 2, 1);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Explorer', '2025-08-19 15:00:00', '2025-08-19 16:20:00', FALSE, 3, '2025-08-19', 1, 4);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Explorer', '2025-08-17 10:30:00', '2025-08-17 12:00:00', TRUE, 4, '2025-08-17', 3, 2);

-- 샘플 게임 데이터 (Time Attack 모드)
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Time Attack', '2025-08-21 15:00:00', '2025-08-21 15:25:00', TRUE, 5, '2025-08-21', 1, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Time Attack', '2025-08-20 12:00:00', '2025-08-20 12:18:00', TRUE, 5, '2025-08-20', 2, 1);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100001, 'Time Attack', '2025-08-19 16:00:00', '2025-08-19 16:35:00', FALSE, 3, '2025-08-19', 4, 0);

INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Time Attack', '2025-08-21 09:00:00', '2025-08-21 09:22:00', TRUE, 5, '2025-08-21', 3, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Time Attack', '2025-08-20 17:00:00', '2025-08-20 17:15:00', TRUE, 5, '2025-08-20', 1, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100002, 'Time Attack', '2025-08-18 13:00:00', '2025-08-18 13:30:00', TRUE, 4, '2025-08-18', 2, 1);

INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Time Attack', '2025-08-21 11:00:00', '2025-08-21 11:28:00', TRUE, 5, '2025-08-21', 5, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Time Attack', '2025-08-19 14:00:00', '2025-08-19 14:20:00', TRUE, 5, '2025-08-19', 1, 0);
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (100003, 'Time Attack', '2025-08-18 10:00:00', '2025-08-18 10:45:00', FALSE, 2, '2025-08-18', 4, 2);