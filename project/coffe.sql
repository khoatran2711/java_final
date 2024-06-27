
USE Coffe_DB;


SELECT DATE_FORMAT(CURDATE(), '%d/%m/%Y');

CREATE TABLE Quyen (
	ID INT NOT NULL  AUTO_INCREMENT,
	tenQuyen VARCHAR(256),
	PRIMARY KEY (ID)
);

CREATE TABLE ThongTinNguoiDung (
	ID INT  NOT NULL AUTO_INCREMENT,
	hoVaTen Varchar(256),
	ngaySinh Date,
	diaChi VARCHAR(256),
	taiKhoan VARCHAR(256),
	matKhau VARCHAR(256),
	IDQuyen INT,
	FOREIGN KEY(IDQuyen) REFERENCES Quyen(ID),
	PRIMARY KEY (ID)
);
CREATE TABLE VatPham(
	ID INT NOT NULL AUTO_INCREMENT,
	tenVatPham VARCHAR(256),
	donGia VARCHAR(256),
	PRIMARY KEY (ID)
	
);
CREATE TABLE Ban (
	soBan INT NOT NULL AUTO_INCREMENT,
	trangThaiTrong BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (soBan)
);

CREATE TABLE HoaDon (
	ID INT NOT NULL AUTO_INCREMENT,
	soBan INT,
	checkIn DATETIME ,
	checkOut DATETIME ,
	idNhanVien INT DEFAULT NULL,
	isPayed BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (ID),
	FOREIGN KEY(soBan) REFERENCES Ban(soBan),
	FOREIGN KEY(idNhanVien) REFERENCES ThongTinNguoiDung(ID)
);

CREATE TABLE OrderItem(
	IDHoaDon INT,
	IDVatPham INT,
	soLuong INT,
	thoiGianGoiMon TIME,
	FOREIGN KEY(IDVatPham) REFERENCES VatPham(ID),
	FOREIGN KEY(IDHoaDon) REFERENCES HoaDon(ID)
);

INSERT INTO quyen VALUES (1,'admin'), (2, 'Nhân viên');

INSERT INTO thongtinnguoidung( hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, IDQuyen) VALUES  ('khoa', "2003-11-12", 'Ninh Binh', 'admin','admin',1);
INSERT INTO thongtinnguoidung( hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, IDQuyen) VALUES  ('Van A', "2003-12-12", 'soc son', 'danThuong','danThuong',2);
INSERT INTO thongtinnguoidung( hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, IDQuyen) VALUES  ('Van Q', "2003-12-12", 'q', 'q','q',2);

INSERT INTO vatpham(tenVatPham, donGia) VALUES('cà phê đen', 20000);
INSERT INTO vatpham(tenVatPham, donGia) VALUES('cà phê sữa', 25000);
INSERT INTO vatpham(tenVatPham, donGia) VALUES('ca pô chi lô', 40000);


INSERT INTO ban (soBan, trangThaiTrong) VALUES (1, TRUE);
INSERT INTO ban (soBan, trangThaiTrong) VALUES (2, FALSE);
INSERT INTO ban (soBan, trangThaiTrong) VALUES (3, TRUE);
INSERT INTO ban (soBan, trangThaiTrong) VALUES (4, TRUE);

INSERT INTO hoadon (soBan, checkIn, checkOut, idNhanVien, isPayed) VALUES (2,"2018-12-12 23:50:55", NULL , NULL, FALSE);
INSERT INTO orderitem(IDhoaDon, idvatPham, soluong, thoiGianGoiMon) VALUES( 1, 2 , 2 , "06:45:00");
INSERT INTO orderitem(IDhoaDon, idvatPham, soluong, thoiGianGoiMon) VALUES( 1, 3 , 1 , "06:45:00");
INSERT INTO orderitem(IDhoaDon, idvatPham, soluong, thoiGianGoiMon) VALUES( 1, 1 , 2 , "06:45:00");

select * FROM quyen WHERE id = 1
select * FROM thongtinnguoidung;
select * FROM vatpham;
select * FROM hoadon;
select * FROM orderitem;
select * FROM ban;


INNER JOIN thongtinnguoidung ON thongtinnguoidung.ID = hoadon.IDNhanVien, thongtinnguoidung.hoVaTen

SELECT hoadon.ID, hoadon.soBan, hoadon.checkIn, hoadon.checkOut, vatpham.tenVatPham, orderitem.soLuong, vatpham.donGia, orderitem.thoiGianGoiMon
FROM hoadon
INNER JOIN orderitem ON orderitem.IDhoaDon = hoadon.ID
INNER JOIN vatpham ON vatpham.ID = orderitem.IDVatPham
WHERE soBan = 2 AND hoadon.isPayed = 0
ORDER BY orderitem.thoiGianGoiMon ASC

SELECT * FROM hoadon

UPDATE hoadon SET checkOut = "2022-12-12 23:50:55", idNhanVien = 2, isPayed = 1 WHERE hoadon.ID = 1

SELECT * FROM thongtinnguoidung

UPDATE ban SET trangThaiTrong = 1 WHERE ban.soBan = 2

INSERT INTO hoadon (soBan, checkIn, checkOut, idNhanVien, isPayed) VALUES (2,"2018-12-12 23:50:55", NULL , NULL, FALSE);
INSERT INTO orderitem(IDhoaDon, idvatPham, soluong, thoiGianGoiMon) VALUES( 1, 2 , 2 , "06:45:00");

UPDATE ban SET trangThaiTrong = 0 WHERE ban.soBan = 2

SELECT * FROM orderitem  INNER JOIN hoadon ON orderitem.IDHoaDon = hoadon.id INNER JOIN vatpham on vatpham.ID = orderitem.IDVatPham

UPDATE hoadon SET checkOut = "", idNhanVien = 2, isPayed = 1 WHERE hoadon.ID = 1

SELECT * FROM vatpham WHERE tenVatPham = "";

UPDATE vatpham SET tenVatPham = "ko", donGia = 0 WHERE id = 5

DELETE FROM vatpham WHERE ID = 2;

SELECT hoadon.ID, hoadon.soBan, hoadon.checkIn, hoadon.checkOut, sum(orderitem.soLuong * vatpham.donGia) AS "TongTien", thongtinnguoidung.hoVaTen FROM orderitem 
INNER JOIN hoadon ON orderitem.IDHoaDon = hoadon.ID
INNER JOIN vatpham ON orderitem.IDVatPham = vatpham.ID
INNER JOIN thongtinnguoidung ON thongtinnguoidung.ID = hoadon.idNhanVien
GROUP BY orderitem.IDHoaDon 

select * from thongtinnguoidung 
inner join quyen on quyen.ID = thongtinnguoidung.IDQuyen 


select * from thongtinnguoidung 
inner join quyen on quyen.ID = thongtinnguoidung.IDQuyen 

INSERT INTO thongtinnguoidung( hoVaTen,ngaySinh, diaChi, taiKhoan, matKhau, IDquyen) VALUES ( 'q2', "2003-12-12" ,'q3','q4','q5',1 );
INSERT INTO thongtinnguoidung( hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, IDQuyen) VALUES  ('Van Q', "2003-12-12", 'q', 'q','q',2);

DELETE FROM thongtinnguoidung WHERE ID = 4;

SELECT * FROM thongtinnguoidung INNER JOIN quyen ON quyen.ID = thongtinnguoidung.IDQuyen GROUP BY thongtinnguoidung.id DESC;