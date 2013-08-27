nominationsystem
================
指名システムについて
  本アプリの制作においては、pleiades（Kepler Java）の日本語版を使用
  管理者が配布したpleiadesフォルダを後述の「pleiadesフォルダの配置について」に記載の場所に
  配置し、別紙「GitHub～Eclipse設定.ods」の手順に従い、GitHubの当リポジトリから資源を適用し
  開発を行う。

pleiadesについて

	pleiadesフォルダの配置について
		pleiadesフォルダをCドライブ直下に配置し、以下のexeからEclipseを起動する。
			▼C:\pleiades\eclipse\eclipse.exe

	指名システムの起動について
		pleiadesで指名システムを起動させるために必要な物は下記のとおりとなる。
			▼mysql-5.6.11（インストールし、サービスを立ち上げている必要がある。）
				→ ID   ：xxxxxxxx（詳細は管理者へ確認）
				  PASS ：xxxxxxxx（詳細は管理者へ確認）
				  DB   ：nominationsystem
				  Table
				  		mysql> show columns from users;
							+----------+-------------+------+-----+---------+-------+
							| Field    | Type        | Null | Key | Default | Extra |
							+----------+-------------+------+-----+---------+-------+
							| id       | varchar(20) | NO   | PRI | NULL    |       |
							| password | varchar(20) | YES  |     | NULL    |       |
							| name     | varchar(40) | YES  |     | NULL    |       |
							| th       | int(11)     | YES  |     | 0       |       |
							+----------+-------------+------+-----+---------+-------+
				  		mysql> show columns from trainee;
				  			+------------+-------------+------+-----+---------+-------+
							| Field      | Type        | Null | Key | Default | Extra |
							+------------+-------------+------+-----+---------+-------+
							| id         | int(11)     | NO   | PRI | NULL    |       |
							| company_id | int(11)     | YES  |     | NULL    |       |
							| name       | varchar(64) | YES  |     | NULL    |       |
							| age        | int(11)     | YES  |     | NULL    |       |
							| replies    | int(11)     | YES  |     | 0       |       |
							+------------+-------------+------+-----+---------+-------+
				  		mysql> show columns from company;
							+-------+--------------+------+-----+---------+-------+
							| Field | Type         | Null | Key | Default | Extra |
							+-------+--------------+------+-----+---------+-------+
							| id    | int(11)      | NO   | PRI | NULL    |       |
							| name  | varchar(100) | YES  |     | NULL    |       |
							+-------+--------------+------+-----+---------+-------+
				  		mysql> show columns from star;
							+-------+---------+------+-----+---------+-------+
							| Field | Type    | Null | Key | Default | Extra |
							+-------+---------+------+-----+---------+-------+
							| id    | int(11) | NO   | PRI | NULL    |       |
							| rank  | int(11) | YES  |     | NULL    |       |
							+-------+---------+------+-----+---------+-------+
						mysql> show columns from picture;
							+---------+--------------+------+-----+---------+----------------+
							| Field   | Type         | Null | Key | Default | Extra          |
							+---------+--------------+------+-----+---------+----------------+
							| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
							| address | varchar(100) | YES  |     | NULL    |                |
							+---------+--------------+------+-----+---------+----------------+

MYSQLメモ

	データベース作成
		CREATE DATABASE nominationsystem;

	データベース確認
		SHOW DATABASES;

	データベース選択
		USE nominationsystem;

	テーブル作成
		CREATE TABLE users (id varchar(20) NOT NULL ,password varchar(20) ,name varchar(40) ,th INT(11) DEFAULT 0, PRIMARY KEY (id));
		CREATE TABLE trainee (id INT(11) NOT NULL , company_id INT(11) ,name varchar(40),age INT(11) ,replies INT(11) ,PRIMARY KEY (id));
		CREATE TABLE company (id INT(11) NOT NULL , name VARCHAR(100), PRIMARY KEY (id));
		CREATE TABLE star (id INT(11) NOT NULL , rank INT(11), PRIMARY KEY (id));
		CREATE TABLE picture (id INT(11) NOT NULL AUTO_INCREMENT, address VARCHAR(100), PRIMARY KEY (id));

	テーブル確認
		SHOW TABLES;
		SHOW COLUMNS FROM テーブル名;


