INSERT INTO tbl_brands (id, name) VALUES (1, 'Sara');
INSERT INTO tbl_brands (id, name) VALUES (2, 'H&M');

INSERT INTO tbl_prices_rates (id, rate) VALUES (1, 0.50);
INSERT INTO tbl_prices_rates (id, rate) VALUES (2, 1.00);
INSERT INTO tbl_prices_rates (id, rate) VALUES (3, 1.50);
INSERT INTO tbl_prices_rates (id, rate) VALUES (4, 2.00);

INSERT INTO tbl_products (id,product_code, name, description, stock, updated_user, updated_date) VALUES(1,35455 , 'LION ULTIMATE', 'T-SHIRT LION BLACK size S', 10 , 'admin', CURRENT_TIMESTAMP);
INSERT INTO tbl_products (id, product_code, name, description, stock,  updated_user, updated_date) VALUES(2,35456 , 'UNDER FOR MEN', 'JACKET LEE BROWN size M', 10  , 'admin', CURRENT_TIMESTAMP);

INSERT INTO tbl_prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr, updated_user, updated_date) VALUES (1,'2021-06-14 00:00','2021-12-31 23:59',1,1,0,35.50,'EUR','admin', CURRENT_TIMESTAMP);
INSERT INTO tbl_prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr, updated_user, updated_date) VALUES (1,'2021-06-14 15:00','2021-06-14 18:30',2,1,1,25.45,'EUR','admin', CURRENT_TIMESTAMP);
INSERT INTO tbl_prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr, updated_user, updated_date) VALUES (1,'2021-06-15 00:00','2021-06-15 11:00',3,1,1,30.50,'EUR','admin', CURRENT_TIMESTAMP);
INSERT INTO tbl_prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr, updated_user, updated_date) VALUES (1,'2021-06-15 16:00','2021-12-31 23:59',4,1,1,38.95,'EUR','admin', CURRENT_TIMESTAMP);
INSERT INTO tbl_prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr, updated_user, updated_date) VALUES (2,'2021-06-15 16:00','2021-12-31 23:59',4,2,1,36.95,'EUR','admin', CURRENT_TIMESTAMP);
