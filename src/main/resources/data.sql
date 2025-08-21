-- 카테고리 데이터
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://www.jejunews.com/news/photo/202212/2198897_222733_08.jpg', '제주국제공항');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://api.cdn.visitjeju.net/photomng/imgpath/202110/20/32ec3ee6-fad9-440d-95ea-628ff6453a48.jpg', '협재해수욕장');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://upload.wikimedia.org/wikipedia/commons/2/24/Chunjeyeon_Waterfall_Jeju_Island-_%EC%B2%9C%EC%A0%9C%EC%97%B0%ED%8F%AD%ED%8F%AC.jpg', '천제연폭포');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://api.cdn.visitjeju.net/photomng/imgpath/202409/20/c8df320a-80df-47d9-a541-bf86631e5d51.png', '성산일출봉');
INSERT INTO category_tbl (category_image, category_name) VALUES ('https://pix10.agoda.net/hotelImages/29537497/-1/a686e5e602b55400a2733c2742678724.jpg?ca=25&ce=0&s=414x232', '시리우스호텔');

-- 샘플 위치 데이터
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('제주국제공항', 33.5115, 126.4929, 1, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('협재해수욕장', 33.3939, 126.2394, 2, NULL, 0, 0);
INSERT INTO location_tbl (loc_name, loc_lat, loc_lng, category_id, loc_image, loc_failed, loc_successed) VALUES ('천제연폭포', 33.2473, 126.4171, 3, NULL, 0, 0);

-- 샘플 사용자 데이터
INSERT INTO user_tbl (user_id, nickname, profile_image, registered_date) VALUES (12345, 'TestUser1', 'https://example.com/profile1.jpg', NOW());
INSERT INTO user_tbl (user_id, nickname, profile_image, registered_date) VALUES (67890, 'TestUser2', 'https://example.com/profile2.jpg', NOW());

-- 샘플 게임 데이터
INSERT INTO game_tbl (user_id, game_mode, start_time, end_time, success, loc_count, game_date, category_id, hint_count) VALUES (12345, 'Explorer', '2025-08-21 10:00:00', '2025-08-21 11:30:00', TRUE, 3, '2025-08-21', 1, 1);