CREATE TABLE "job_seekers"(
    "id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "national_id" VARCHAR(255) NOT NULL,
    "date_of_birth" DATE NOT NULL,
    "avatar_url" TEXT NOT NULL,
    "is_email_confirmed" BOOLEAN NOT NULL,
    "is_confirmed_with_mernis" BOOLEAN NOT NULL,
    "is_active" BOOLEAN NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "job_seekers" ADD PRIMARY KEY("id");
ALTER TABLE
    "job_seekers" ADD CONSTRAINT "job_seekers_user_id_unique" UNIQUE("user_id");
ALTER TABLE
    "job_seekers" ADD CONSTRAINT "job_seekers_national_id_unique" UNIQUE("national_id");
	
CREATE TABLE "employers"(
    "id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "company_name" VARCHAR(255) NOT NULL,
    "mobile_number" VARCHAR(255) NOT NULL,
    "is_email_confirmed" BOOLEAN NOT NULL,
    "is_confirmed_by_staff" BOOLEAN NOT NULL,
    "is_active" BOOLEAN NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "employers" ADD PRIMARY KEY("id");
ALTER TABLE
    "employers" ADD CONSTRAINT "employers_user_id_unique" UNIQUE("user_id");
ALTER TABLE
    "employers" ADD CONSTRAINT "employers_mobile_number_unique" UNIQUE("mobile_number");
	
	
CREATE TABLE "job_positions"(
    "id" INTEGER NOT NULL,
    "position_name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "job_positions" ADD PRIMARY KEY("id");
ALTER TABLE
    "job_positions" ADD CONSTRAINT "job_positions_position_name_unique" UNIQUE("position_name");


CREATE TABLE "users"(
    "id" INTEGER NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "users" ADD PRIMARY KEY("id");
ALTER TABLE
    "users" ADD CONSTRAINT "users_email_unique" UNIQUE("email");
	
	
CREATE TABLE "staff"(
    "id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "role" VARCHAR(255) CHECK ("role" IN('ADMIN', 'DEVELOPER', 'OWNER')) NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "staff" ADD PRIMARY KEY("id");
ALTER TABLE
    "staff" ADD CONSTRAINT "staff_user_id_unique" UNIQUE("user_id");
	
	
CREATE TABLE "api_tokens"(
    "id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "token_name" VARCHAR(255) CHECK
        ("token_name" IN('')) NOT NULL,
        "token" VARCHAR(255) NOT NULL,
        "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
        "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "api_tokens" ADD PRIMARY KEY("id");
ALTER TABLE
    "api_tokens" ADD CONSTRAINT "api_tokens_token_unique" UNIQUE("token");

CREATE TABLE "advertisements"(
    "id" INTEGER NOT NULL,
    "employer_id" INTEGER NOT NULL,
    "job_position_id" INTEGER NOT NULL,
    "city_id" INTEGER NOT NULL,
    "title" TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "salary_interval_least" INTEGER NULL,
    "salary_interval_most" INTEGER NULL,
    "available_position_count" INTEGER NOT NULL,
    "last_submit_date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "is_active" BOOLEAN NOT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "advertisements" ADD PRIMARY KEY("id");


CREATE TABLE "cities"(
    "id" INTEGER NOT NULL,
    "city_name" TEXT NOT NULL
);
ALTER TABLE
    "cities" ADD PRIMARY KEY("id");
ALTER TABLE
    "cities" ADD CONSTRAINT "cities_city_name_unique" UNIQUE("city_name");
	
	
CREATE TABLE "curriculum_vitaes"(
    "id" INTEGER NOT NULL,
    "job_seeker_id" INTEGER NOT NULL,
    "cover_letter" TEXT NULL,
    "github_url" TEXT NULL,
    "linkedin_url" TEXT NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "curriculum_vitaes" ADD PRIMARY KEY("id");
	
	
CREATE TABLE "technologies"(
    "id" INTEGER NOT NULL,
    "tech_name" INTEGER NOT NULL
);
ALTER TABLE
    "technologies" ADD PRIMARY KEY("id");
ALTER TABLE
    "technologies" ADD CONSTRAINT "technologies_tech_name_unique" UNIQUE("tech_name");
	
	
CREATE TABLE "languages"(
    "id" VARCHAR(10) NOT NULL,
    "language" TEXT NOT NULL
);
ALTER TABLE
    "languages" ADD PRIMARY KEY("id");
ALTER TABLE
    "languages" ADD CONSTRAINT "languages_language_unique" UNIQUE("language");
	
	
CREATE TABLE "universities"(
    "id" INTEGER NOT NULL,
    "name" TEXT NOT NULL
);

ALTER TABLE
    "universities" ADD PRIMARY KEY("id");
ALTER TABLE
    "universities" ADD CONSTRAINT "universities_name_unique" UNIQUE("name");

	
CREATE TABLE "departments"(
    "id" INTEGER NOT NULL,
    "name" TEXT NOT NULL
);
ALTER TABLE
    "departments" ADD PRIMARY KEY("id");
ALTER TABLE
    "departments" ADD CONSTRAINT "departments_name_unique" UNIQUE("name");

CREATE TABLE "experiences"(
    "id" INTEGER NOT NULL,
    "cv_id" INTEGER NOT NULL,
    "city_id" INTEGER NOT NULL,
    "job_position" TEXT NOT NULL,
    "company_name" TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "salary_interval_least" INTEGER NULL,
    "salary_interval_most" INTEGER NULL,
    "start" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "end" TIMESTAMP(0) WITHOUT TIME ZONE NULL,
    "created_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "updated_at" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL
);
ALTER TABLE
    "experiences" ADD PRIMARY KEY("id");
ALTER TABLE
    "experiences" ADD CONSTRAINT "experiences_cv_id_foreign" FOREIGN KEY("cv_id") REFERENCES "curriculum_vitaes"("id");
	

CREATE TABLE "pivot_user_job_positions"(
    "user_id" INTEGER NOT NULL,
    "job_position_id" INTEGER NOT NULL
);
ALTER TABLE
    "pivot_user_job_positions" ADD CONSTRAINT "pivot_user_job_positions_job_position_id_foreign" FOREIGN KEY("job_position_id") REFERENCES "job_positions"("id");


CREATE TABLE "pivot_job_seeker_education"(
    "id" INTEGER NOT NULL,
    "cv_id" INTEGER NOT NULL,
    "university_id" INTEGER NOT NULL,
    "department_id" INTEGER NOT NULL,
    "start" INTEGER NOT NULL,
    "end" INTEGER NULL,
    "graduation_point" DOUBLE PRECISION NULL
);
ALTER TABLE
    "pivot_job_seeker_education" ADD PRIMARY KEY("id");
ALTER TABLE
    "pivot_job_seeker_education" ADD CONSTRAINT "pivot_job_seeker_education_department_id_foreign" FOREIGN KEY("department_id") REFERENCES "departments"("id");
ALTER TABLE
    "pivot_job_seeker_education" ADD CONSTRAINT "pivot_job_seeker_education_university_id_foreign" FOREIGN KEY("university_id") REFERENCES "universities"("id");
ALTER TABLE
    "pivot_job_seeker_education" ADD CONSTRAINT "pivot_job_seeker_education_cv_id_foreign" FOREIGN KEY("cv_id") REFERENCES "curriculum_vitaes"("id");
	
	
CREATE TABLE "pivot_job_seeker_advertisements"(
    "id" INTEGER NOT NULL,
    "advertisement_id" INTEGER NOT NULL,
    "job_seeker_id" INTEGER NOT NULL
);
ALTER TABLE
    "pivot_job_seeker_advertisements" ADD PRIMARY KEY("id");
ALTER TABLE
    "pivot_job_seeker_advertisements" ADD CONSTRAINT "pivot_job_seeker_advertisements_job_seeker_id_foreign" FOREIGN KEY("job_seeker_id") REFERENCES "job_seekers"("id");

CREATE TABLE "pivot_job_seeker_technologies"(
    "id" INTEGER NOT NULL,
    "cv_id" INTEGER NOT NULL,
    "tech_id" INTEGER NOT NULL,
    "grade" VARCHAR(255) CHECK ("grade" IN('BEGINNER', 'INTERMEDIATE', 'EXPERT')) NOT NULL
);
ALTER TABLE
    "pivot_job_seeker_technologies" ADD PRIMARY KEY("id");
ALTER TABLE
    "pivot_job_seeker_technologies" ADD CONSTRAINT "pivot_job_seeker_technologies_tech_id_foreign" FOREIGN KEY("tech_id") REFERENCES "technologies"("id");
ALTER TABLE
    "pivot_job_seeker_technologies" ADD CONSTRAINT "pivot_job_seeker_technologies_cv_id_foreign" FOREIGN KEY("cv_id") REFERENCES "curriculum_vitaes"("id");

	
CREATE TABLE "pivot_job_seeker_languages"(
    "id" INTEGER NOT NULL,
    "cv_id" INTEGER NOT NULL,
    "lang_id" VARCHAR(10) NOT NULL,
    "grade" VARCHAR(255) CHECK ("grade" IN('BEGINNER', 'PRE_INTERMEDIATE', 'INTERMEDIATE', 'UPPER_INTERMEDIATE', 'ADVANCED')) NOT NULL
);
ALTER TABLE
    "pivot_job_seeker_languages" ADD PRIMARY KEY("id");
ALTER TABLE
    "pivot_job_seeker_languages" ADD CONSTRAINT "pivot_job_seeker_languages_lang_id_foreign" FOREIGN KEY("lang_id") REFERENCES "languages"("id");
ALTER TABLE
    "pivot_job_seeker_languages" ADD CONSTRAINT "pivot_job_seeker_languages_cv_id_foreign" FOREIGN KEY("cv_id") REFERENCES "curriculum_vitaes"("id");
	

ALTER TABLE
    "advertisements" ADD CONSTRAINT "advertisements_job_position_id_foreign" FOREIGN KEY("job_position_id") REFERENCES "job_positions"("id");
ALTER TABLE
    "advertisements" ADD CONSTRAINT "advertisements_city_id_foreign" FOREIGN KEY("city_id") REFERENCES "cities"("id");
ALTER TABLE
    "advertisements" ADD CONSTRAINT "advertisements_employer_id_foreign" FOREIGN KEY("employer_id") REFERENCES "employers"("id");
ALTER TABLE
    "experiences" ADD CONSTRAINT "experiences_city_id_foreign" FOREIGN KEY("city_id") REFERENCES "cities"("id");
ALTER TABLE
    "curriculum_vitaes" ADD CONSTRAINT "curriculum_vitaes_job_seeker_id_foreign" FOREIGN KEY("job_seeker_id") REFERENCES "job_seekers"("id");
ALTER TABLE
    "employers" ADD CONSTRAINT "employers_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "job_seekers" ADD CONSTRAINT "job_seekers_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "api_tokens" ADD CONSTRAINT "api_tokens_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "staff" ADD CONSTRAINT "staff_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "pivot_user_job_positions" ADD CONSTRAINT "pivot_user_job_positions_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "pivot_job_seeker_advertisements" ADD CONSTRAINT "pivot_job_seeker_advertisements_advertisement_id_foreign" FOREIGN KEY("advertisement_id") REFERENCES "advertisements"("id");

	
INSERT INTO universities (id, name) VALUES (1, 'ABDULLAH GÜL ÜNİVERSİTESİ'),
(2, 'ADANA ALPARSLAN TÜRKEŞ BİLİM VE TEKNOLOJİ ÜNİVERSİTESİ'),
(3, 'ADIYAMAN ÜNİVERSİTESİ'),
(4, 'AFYON KOCATEPE ÜNİVERSİTESİ'),
(5, 'AFYONKARAHİSAR SAĞLIK BİLİMLERİ ÜNİVERSİTESİ'),
(6, 'AĞRI İBRAHİM ÇEÇEN ÜNİVERSİTESİ'),
(7, 'AKDENİZ ÜNİVERSİTESİ'),
(8, 'AKSARAY ÜNİVERSİTESİ'),
(9, 'ALANYA ALAADDİN KEYKUBAT ÜNİVERSİTESİ'),
(10, 'AMASYA ÜNİVERSİTESİ'),
(11, 'ANADOLU ÜNİVERSİTESİ'),
(12, 'ANKARA HACI BAYRAM VELİ ÜNİVERSİTESİ'),
(13, 'ANKARA SOSYAL BİLİMLER ÜNİVERSİTESİ'),
(14, 'ANKARA ÜNİVERSİTESİ'),
(15, 'ANKARA YILDIRIM BEYAZIT ÜNİVERSİTESİ'),
(16, 'ARDAHAN ÜNİVERSİTESİ'),
(17, 'ARTVİN ÇORUH ÜNİVERSİTESİ'),
(18, 'ATATÜRK ÜNİVERSİTESİ'),
(19, 'AYDIN ADNAN MENDERES ÜNİVERSİTESİ'),
(20, 'BALIKESİR ÜNİVERSİTESİ'),
(21, 'BANDIRMA ONYEDİ EYLÜL ÜNİVERSİTESİ'),
(22, 'BARTIN ÜNİVERSİTESİ'),
(23, 'BATMAN ÜNİVERSİTESİ'),
(24, 'BAYBURT ÜNİVERSİTESİ'),
(25, 'BİLECİK ŞEYH EDEBALİ ÜNİVERSİTESİ'),
(26, 'BİNGÖL ÜNİVERSİTESİ'),
(27, 'BİTLİS EREN ÜNİVERSİTESİ'),
(28, 'BOĞAZİÇİ ÜNİVERSİTESİ'),
(29, 'BOLU ABANT İZZET BAYSAL ÜNİVERSİTESİ'),
(30, 'BURDUR MEHMET AKİF ERSOY ÜNİVERSİTESİ'),
(31, 'BURSA TEKNİK ÜNİVERSİTESİ'),
(32, 'BURSA ULUDAĞ ÜNİVERSİTESİ'),
(33, 'ÇANAKKALE ONSEKİZ MART ÜNİVERSİTESİ'),
(34, 'ÇANKIRI KARATEKİN ÜNİVERSİTESİ'),
(35, 'ÇUKUROVA ÜNİVERSİTESİ'),
(36, 'DİCLE ÜNİVERSİTESİ'),
(37, 'DOKUZ EYLÜL ÜNİVERSİTESİ'),
(38, 'DÜZCE ÜNİVERSİTESİ'),
(39, 'EGE ÜNİVERSİTESİ'),
(40, 'ERCİYES ÜNİVERSİTESİ'),
(41, 'ERZİNCAN BİNALİ YILDIRIM ÜNİVERSİTESİ'),
(42, 'ERZURUM TEKNİK ÜNİVERSİTESİ'),
(43, 'ESKİŞEHİR OSMANGAZİ ÜNİVERSİTESİ'),
(44, 'ESKİŞEHİR TEKNİK ÜNİVERSİTESİ'),
(45, 'FIRAT ÜNİVERSİTESİ'),
(46, 'GALATASARAY ÜNİVERSİTESİ'),
(47, 'GAZİ ÜNİVERSİTESİ'),
(48, 'GAZİANTEP İSLAM BİLİM VE TEKNOLOJİ ÜNİVERSİTESİ'),
(49, 'GAZİANTEP ÜNİVERSİTESİ'),
(50, 'GEBZE TEKNİK ÜNİVERSİTESİ'),
(51, 'GİRESUN ÜNİVERSİTESİ'),
(52, 'GÜMÜŞHANE ÜNİVERSİTESİ'),
(53, 'HACETTEPE ÜNİVERSİTESİ'),
(54, 'HAKKARİ ÜNİVERSİTESİ'),
(55, 'HARRAN ÜNİVERSİTESİ'),
(56, 'HATAY MUSTAFA KEMAL ÜNİVERSİTESİ'),
(57, 'HİTİT ÜNİVERSİTESİ'),
(58, 'IĞDIR ÜNİVERSİTESİ'),
(59, 'ISPARTA UYGULAMALI BİLİMLER ÜNİVERSİTESİ'),
(60, 'İNÖNÜ ÜNİVERSİTESİ'),
(61, 'İSKENDERUN TEKNİK ÜNİVERSİTESİ'),
(62, 'İSTANBUL MEDENİYET ÜNİVERSİTESİ'),
(63, 'İSTANBUL TEKNİK ÜNİVERSİTESİ'),
(64, 'İSTANBUL ÜNİVERSİTESİ'),
(65, 'İSTANBUL ÜNİVERSİTESİ-CERRAHPAŞA'),
(66, 'İZMİR BAKIRÇAY ÜNİVERSİTESİ'),
(67, 'İZMİR DEMOKRASİ ÜNİVERSİTESİ'),
(68, 'İZMİR KATİP ÇELEBİ ÜNİVERSİTESİ'),
(69, 'İZMİR YÜKSEK TEKNOLOJİ ENSTİTÜSÜ'),
(70, 'KAFKAS ÜNİVERSİTESİ'),
(71, 'KAHRAMANMARAŞ İSTİKLAL ÜNİVERSİTESİ'),
(72, 'KAHRAMANMARAŞ SÜTÇÜ İMAM ÜNİVERSİTESİ'),
(73, 'KARABÜK ÜNİVERSİTESİ'),
(74, 'KARADENİZ TEKNİK ÜNİVERSİTESİ'),
(75, 'KARAMANOĞLU MEHMETBEY ÜNİVERSİTESİ'),
(76, 'KASTAMONU ÜNİVERSİTESİ'),
(77, 'KAYSERİ ÜNİVERSİTESİ'),
(78, 'KIRIKKALE ÜNİVERSİTESİ'),
(79, 'KIRKLARELİ ÜNİVERSİTESİ'),
(80, 'KIRŞEHİR AHİ EVRAN ÜNİVERSİTESİ'),
(81, 'KİLİS 7 ARALIK ÜNİVERSİTESİ'),
(82, 'KOCAELİ ÜNİVERSİTESİ'),
(83, 'KONYA TEKNİK ÜNİVERSİTESİ'),
(84, 'KÜTAHYA DUMLUPINAR ÜNİVERSİTESİ'),
(85, 'KÜTAHYA SAĞLIK BİLİMLERİ ÜNİVERSİTESİ'),
(86, 'MALATYA TURGUT ÖZAL ÜNİVERSİTESİ'),
(87, 'MANİSA CELÂL BAYAR ÜNİVERSİTESİ'),
(88, 'MARDİN ARTUKLU ÜNİVERSİTESİ'),
(89, 'MARMARA ÜNİVERSİTESİ'),
(90, 'MERSİN ÜNİVERSİTESİ'),
(91, 'MİMAR SİNAN GÜZEL SANATLAR ÜNİVERSİTESİ'),
(92, 'MUĞLA SITKI KOÇMAN ÜNİVERSİTESİ'),
(93, 'MUNZUR ÜNİVERSİTESİ'),
(94, 'MUŞ ALPARSLAN ÜNİVERSİTESİ'),
(95, 'NECMETTİN ERBAKAN ÜNİVERSİTESİ'),
(96, 'NEVŞEHİR HACI BEKTAŞ VELİ ÜNİVERSİTESİ'),
(97, 'NİĞDE ÖMER HALİSDEMİR ÜNİVERSİTESİ'),
(98, 'ONDOKUZ MAYIS ÜNİVERSİTESİ'),
(99, 'ORDU ÜNİVERSİTESİ'),
(100, 'ORTA DOĞU TEKNİK ÜNİVERSİTESİ'),
(101, 'OSMANİYE KORKUT ATA ÜNİVERSİTESİ'),
(102, 'PAMUKKALE ÜNİVERSİTESİ'),
(103, 'RECEP TAYYİP ERDOĞAN ÜNİVERSİTESİ'),
(104, 'SAĞLIK BİLİMLERİ ÜNİVERSİTESİ'),
(105, 'SAKARYA UYGULAMALI BİLİMLER ÜNİVERSİTESİ'),
(106, 'SAKARYA ÜNİVERSİTESİ'),
(107, 'SAMSUN ÜNİVERSİTESİ'),
(108, 'SELÇUK ÜNİVERSİTESİ'),
(109, 'SİİRT ÜNİVERSİTESİ'),
(110, 'SİNOP ÜNİVERSİTESİ'),
(111, 'SİVAS CUMHURİYET ÜNİVERSİTESİ'),
(112, 'SÜLEYMAN DEMİREL ÜNİVERSİTESİ'),
(113, 'ŞIRNAK ÜNİVERSİTESİ'),
(114, 'TARSUS ÜNİVERSİTESİ'),
(115, 'TEKİRDAĞ NAMIK KEMAL ÜNİVERSİTESİ'),
(116, 'TOKAT GAZİOSMANPAŞA ÜNİVERSİTESİ'),
(117, 'TRABZON ÜNİVERSİTESİ'),
(118, 'TRAKYA ÜNİVERSİTESİ'),
(119, 'TÜRK-ALMAN ÜNİVERSİTESİ'),
(120, 'UŞAK ÜNİVERSİTESİ'),
(121, 'VAN YÜZÜNCÜ YIL ÜNİVERSİTESİ'),
(122, 'YALOVA ÜNİVERSİTESİ'),
(123, 'YILDIZ TEKNİK ÜNİVERSİTESİ'),
(124, 'YOZGAT BOZOK ÜNİVERSİTESİ'),
(125, 'ZONGULDAK BÜLENT ECEVİT ÜNİVERSİTESİ'),
(126, 'ACIBADEM MEHMET ALİ AYDINLAR ÜNİVERSİTESİ'),
(127, 'ALANYA HAMDULLAH EMİN PAŞA ÜNİVERSİTESİ'),
(128, 'ALTINBAŞ ÜNİVERSİTESİ'),
(129, 'ANKARA BİLİM ÜNİVERSİTESİ'),
(130, 'ANKARA MEDİPOL ÜNİVERSİTESİ'),
(131, 'ANTALYA AKEV ÜNİVERSİTESİ'),
(132, 'ANTALYA BİLİM ÜNİVERSİTESİ'),
(133, 'ATILIM ÜNİVERSİTESİ'),
(134, 'AVRASYA ÜNİVERSİTESİ'),
(135, 'BAHÇEŞEHİR ÜNİVERSİTESİ'),
(136, 'BAŞKENT ÜNİVERSİTESİ'),
(137, 'BEYKENT ÜNİVERSİTESİ'),
(138, 'BEYKOZ ÜNİVERSİTESİ'),
(139, 'BEZM-İ ÂLEM VAKIF ÜNİVERSİTESİ'),
(140, 'BİRUNİ ÜNİVERSİTESİ'),
(141, 'ÇAĞ ÜNİVERSİTESİ'),
(142, 'ÇANKAYA ÜNİVERSİTESİ'),
(143, 'DEMİROĞLU BİLİM ÜNİVERSİTESİ'),
(144, 'DOĞUŞ ÜNİVERSİTESİ'),
(145, 'FATİH SULTAN MEHMET VAKIF ÜNİVERSİTESİ'),
(146, 'FENERBAHÇE ÜNİVERSİTESİ'),
(147, 'HALİÇ ÜNİVERSİTESİ'),
(148, 'HASAN KALYONCU ÜNİVERSİTESİ'),
(149, 'IŞIK ÜNİVERSİTESİ'),
(150, 'İBN HALDUN ÜNİVERSİTESİ'),
(151, 'İHSAN DOĞRAMACI BİLKENT ÜNİVERSİTESİ'),
(152, 'İSTANBUL 29 MAYIS ÜNİVERSİTESİ'),
(153, 'İSTANBUL AREL ÜNİVERSİTESİ'),
(154, 'İSTANBUL ATLAS ÜNİVERSİTESİ'),
(155, 'İSTANBUL AYDIN ÜNİVERSİTESİ'),
(156, 'İSTANBUL AYVANSARAY ÜNİVERSİTESİ'),
(157, 'İSTANBUL BİLGİ ÜNİVERSİTESİ'),
(158, 'İSTANBUL ESENYURT ÜNİVERSİTESİ'),
(159, 'İSTANBUL GALATA ÜNİVERSİTESİ'),
(160, 'İSTANBUL GEDİK ÜNİVERSİTESİ'),
(161, 'İSTANBUL GELİŞİM ÜNİVERSİTESİ'),
(162, 'İSTANBUL KENT ÜNİVERSİTESİ'),
(163, 'İSTANBUL KÜLTÜR ÜNİVERSİTESİ'),
(164, 'İSTANBUL MEDİPOL ÜNİVERSİTESİ'),
(165, 'İSTANBUL OKAN ÜNİVERSİTESİ'),
(166, 'İSTANBUL RUMELİ ÜNİVERSİTESİ'),
(167, 'İSTANBUL SABAHATTİN ZAİM ÜNİVERSİTESİ'),
(168, 'İSTANBUL SAĞLIK VE TEKNOLOJİ ÜNİVERSİTESİ'),
(169, 'İSTANBUL TİCARET ÜNİVERSİTESİ'),
(170, 'İSTANBUL YENİ YÜZYIL ÜNİVERSİTESİ'),
(171, 'İSTİNYE ÜNİVERSİTESİ'),
(172, 'İZMİR EKONOMİ ÜNİVERSİTESİ'),
(173, 'İZMİR TINAZTEPE ÜNİVERSİTESİ'),
(174, 'KADİR HAS ÜNİVERSİTESİ'),
(175, 'KAPADOKYA ÜNİVERSİTESİ'),
(176, 'KOCAELİ SAĞLIK VE TEKNOLOJİ ÜNİVERSİTESİ'),
(177, 'KOÇ ÜNİVERSİTESİ'),
(178, 'KONYA GIDA VE TARIM ÜNİVERSİTESİ'),
(179, 'KTO KARATAY ÜNİVERSİTESİ'),
(180, 'LOKMAN HEKİM ÜNİVERSİTESİ'),
(181, 'MALTEPE ÜNİVERSİTESİ'),
(182, 'MEF ÜNİVERSİTESİ'),
(183, 'NİŞANTAŞI ÜNİVERSİTESİ'),
(184, 'NUH NACİ YAZGAN ÜNİVERSİTESİ'),
(185, 'OSTİM TEKNİK ÜNİVERSİTESİ'),
(186, 'ÖZYEĞİN ÜNİVERSİTESİ'),
(187, 'PİRİ REİS ÜNİVERSİTESİ'),
(188, 'SABANCI ÜNİVERSİTESİ'),
(189, 'SANKO ÜNİVERSİTESİ'),
(190, 'TED ÜNİVERSİTESİ'),
(191, 'TOBB EKONOMİ VE TEKNOLOJİ ÜNİVERSİTESİ'),
(192, 'TOROS ÜNİVERSİTESİ'),
(193, 'TÜRK HAVA KURUMU ÜNİVERSİTESİ'),
(194, 'UFUK ÜNİVERSİTESİ'),
(195, 'ÜSKÜDAR ÜNİVERSİTESİ'),
(196, 'YAŞAR ÜNİVERSİTESİ'),
(197, 'YEDİTEPE ÜNİVERSİTESİ'),
(198, 'YÜKSEK İHTİSAS ÜNİVERSİTESİ'),
(199, 'KKTC Üniversiteleri'),
(200, 'ADA KENT ÜNİVERSİTESİ'),
(201, 'AKDENİZ KARPAZ ÜNİVERSİTESİ'),
(202, 'ARKIN YARATICI SANATLAR VE TASARIM ÜNİVERSİTESİ'),
(203, 'BAHÇEŞEHİR KIBRIS ÜNİVERSİTESİ'),
(204, 'DOĞU AKDENİZ ÜNİVERSİTESİ'),
(205, 'GİRNE AMERİKAN ÜNİVERSİTESİ'),
(206, 'GİRNE ÜNİVERSİTESİ'),
(207, 'KIBRIS AMERİKAN ÜNİVERSİTESİ'),
(208, 'KIBRIS BATI ÜNİVERSİTESİ'),
(209, 'KIBRIS İLİM ÜNİVERSİTESİ'),
(210, 'KIBRIS SAĞLIK VE TOPLUM BİLİMLERİ ÜNİVERSİTESİ'),
(211, 'LEFKE AVRUPA ÜNİVERSİTESİ'),
(212, 'RAUF DENKTAŞ ÜNİVERSİTESİ'),
(213, 'ULUSLARARASI FİNAL ÜNİVERSİTESİ'),
(214, 'ULUSLARARASI KIBRIS ÜNİVERSİTESİ'),
(215, 'YAKIN DOĞU ÜNİVERSİTESİ'),
(216, 'Yurtdışı Üniversiteleri'),
(217, 'AZERBAYCAN DEVLET PEDAGOJİ ÜNİVERSİTESİ'),
(218, 'AZERBAYCAN DİLLER ÜNİVERSİTESİ'),
(219, 'AZERBAYCAN TIP ÜNİVERSİTESİ'),
(220, 'BAKÜ DEVLET ÜNİVERSİTESİ'),
(221, 'HOCA AHMET YESEVİ ULUSLARARASI TÜRK-KAZAK ÜNİVERSİTESİ'),
(222, 'KIRGIZİSTAN-TÜRKİYE MANAS ÜNİVERSİTESİ'),
(223, 'KOMRAT DEVLET ÜNİVERSİTESİ'),
(224, 'ULUSLARARASI BALKAN ÜNİVERSİTESİ'),
(225, 'ULUSLARARASI SARAYBOSNA ÜNİVERSİTESİ');
	
INSERT INTO departments (id, name) VALUES (1, 'ACİL YARDIM VE AFET YÖNETİMİ(FAKÜLTE)'),
(2, 'ACİL YARDIM VE AFET YÖNETİMİ(YÜKSEKOKUL)'),
(3, 'ADLİ BİLİMLER'),
(4, 'ADLİ BİLİŞİM MÜHENDİSLİĞİ'),
(5, 'ADLİ BİLİŞİM MÜHENDİSLİĞİ(M.T.O.K.)'),
(6, 'AĞAÇ İŞLERİ ENDÜSTRİ MÜHENDİSLİĞİ'),
(7, 'AİLE VE TÜKETİCİ BİLİMLERİ'),
(8, 'AKTÜERYA BİLİMLERİ(FAKÜLTE)'),
(9, 'AKTÜERYA BİLİMLERİ(YÜKSEKOKUL)'),
(10, 'ALMAN DİLİ VE EDEBİYATI'),
(11, 'ALMANCA MÜTERCİM VE TERCÜMANLIK(FAKÜLTE)'),
(12, 'ALMANCA MÜTERCİM VE TERCÜMANLIK(YÜKSEKOKUL)'),
(13, 'ALMANCA ÖĞRETMENLİĞİ'),
(14, 'AMERİKAN KÜLTÜRÜ VE EDEBİYATI'),
(15, 'ANTRENÖRLÜK EĞİTİMİ(FAKÜLTE)'),
(16, 'ANTRENÖRLÜK EĞİTİMİ(YÜKSEKOKUL)'),
(17, 'ANTROPOLOJİ'),
(18, 'ARAP DİLİ VE EDEBİYATI'),
(19, 'ARAPÇA MÜTERCİM VE TERCÜMANLIK(FAKÜLTE)'),
(20, 'ARAPÇA MÜTERCİM VE TERCÜMANLIK(YÜKSEKOKUL)'),
(21, 'ARAPÇA ÖĞRETMENLİĞİ'),
(22, 'ARKEOLOJİ'),
(23, 'ARKEOLOJİ VE SANAT TARİHİ'),
(24, 'ARNAVUT DİLİ VE EDEBİYATI'),
(25, 'ASTRONOMİ VE UZAY BİLİMLERİ'),
(26, 'AYAKKABI TASARIMI VE ÜRETİMİ'),
(27, 'AZERBAYCAN TÜRKÇESİ VE EDEBİYATI'),
(28, 'BAHÇE BİTKİLERİ'),
(29, 'BALIKÇILIK TEKNOLOJİSİ MÜHENDİSLİĞİ'),
(30, 'BANKACILIK'),
(31, 'BANKACILIK VE FİNANS'),
(32, 'BANKACILIK VE SİGORTACILIK(FAKÜLTE)'),
(33, 'BANKACILIK VE SİGORTACILIK(YÜKSEKOKUL)'),
(34, 'BASIM TEKNOLOJİLERİ(YÜKSEKOKUL)'),
(35, 'BASIN VE YAYIN'),
(36, 'BATI DİLLERİ'),
(37, 'BEDEN EĞİTİMİ VE SPOR ÖĞRETMENLİĞİ(FAKÜLTE)'),
(38, 'BEDEN EĞİTİMİ VE SPOR ÖĞRETMENLİĞİ(YÜKSEKOKUL)'),
(39, 'BESLENME VE DİYETETİK(FAKÜLTE)'),
(40, 'BESLENME VE DİYETETİK(YÜKSEKOKUL)'),
(41, 'BİLGİ GÜVENLİĞİ TEKNOLOJİSİ(FAKÜLTE)'),
(42, 'BİLGİ GÜVENLİĞİ TEKNOLOJİSİ(YÜKSEKOKUL)'),
(43, 'BİLGİ VE BELGE YÖNETİMİ'),
(44, 'BİLGİSAYAR BİLİMLERİ'),
(45, 'BİLGİSAYAR MÜHENDİSLİĞİ'),
(46, 'BİLGİSAYAR MÜHENDİSLİĞİ(M.T.O.K.)'),
(47, 'BİLGİSAYAR TEKNOLOJİSİ VE BİLİŞİM SİSTEMLERİ'),
(48, 'BİLGİSAYAR VE ÖĞRETİM TEKNOLOJİLERİ ÖĞRETMENLİĞİ'),
(49, 'BİLİM TARİHİ'),
(50, 'BİLİŞİM SİSTEMLERİ MÜHENDİSLİĞİ'),
(51, 'BİLİŞİM SİSTEMLERİ MÜHENDİSLİĞİ(M.T.O.K.)'),
(52, 'BİLİŞİM SİSTEMLERİ VE TEKNOLOJİLERİ(FAKÜLTE)'),
(53, 'BİLİŞİM SİSTEMLERİ VE TEKNOLOJİLERİ(YÜKSEKOKUL)'),
(54, 'BİTKİ KORUMA'),
(55, 'BİTKİSEL ÜRETİM VE TEKNOLOJİLERİ'),
(56, 'BİYOENFORMATİK VE GENETİK'),
(57, 'BİYOKİMYA'),
(58, 'BİYOLOJİ'),
(59, 'BİYOLOJİ ÖĞRETMENLİĞİ'),
(60, 'BİYOMEDİKAL MÜHENDİSLİĞİ'),
(61, 'BİYOMEDİKAL MÜHENDİSLİĞİ(M.T.O.K.)'),
(62, 'BİYOMÜHENDİSLİK'),
(63, 'BİYOSİSTEM MÜHENDİSLİĞİ'),
(64, 'BİYOTEKNOLOJİ'),
(65, 'BOŞNAK DİLİ VE EDEBİYATI'),
(66, 'BULGAR DİLİ VE EDEBİYATI'),
(67, 'BULGARCA MÜTERCİM VE TERCÜMANLIK'),
(68, 'CEVHER HAZIRLAMA MÜHENDİSLİĞİ'),
(69, 'COĞRAFYA'),
(70, 'COĞRAFYA(AÇIKÖĞRETİM)'),
(71, 'COĞRAFYA ÖĞRETMENLİĞİ'),
(72, 'ÇAĞDAŞ TÜRK LEHÇELERİ VE EDEBİYATLARI'),
(73, 'ÇAĞDAŞ YUNAN DİLİ VE EDEBİYATI'),
(74, 'ÇALIŞMA EKONOMİSİ VE ENDÜSTRİ İLİŞKİLERİ'),
(75, 'ÇALIŞMA EKONOMİSİ VE ENDÜSTRİ İLİŞKİLERİ(AÇIKÖĞRETİM)'),
(76, 'ÇERKEZ DİLİ VE KÜLTÜRÜ'),
(77, 'ÇEVİRİBİLİMİ'),
(78, 'ÇEVRE MÜHENDİSLİĞİ'),
(79, 'ÇİN DİLİ VE EDEBİYATI'),
(80, 'ÇİNCE MÜTERCİM VE TERCÜMANLIK'),
(81, 'ÇİZGİ FİLM VE ANİMASYON'),
(82, 'ÇOCUK GELİŞİMİ(AÇIKÖĞRETİM)'),
(83, 'ÇOCUK GELİŞİMİ(FAKÜLTE)'),
(84, 'ÇOCUK GELİŞİMİ(YÜKSEKOKUL)'),
(85, 'DENİZ BİLİMLERİ VE TEKNOLOJİSİ MÜHENDİSLİĞİ'),
(86, 'DENİZ ULAŞTIRMA İŞLETME MÜHENDİSLİĞİ(FAKÜLTE)'),
(87, 'DENİZ ULAŞTIRMA İŞLETME MÜHENDİSLİĞİ(YÜKSEKOKUL)'),
(88, 'DENİZCİLİK İŞLETMELERİ YÖNETİMİ'),
(89, 'DERİ MÜHENDİSLİĞİ'),
(90, 'DİJİTAL OYUN TASARIMI'),
(91, 'DİL VE KONUŞMA TERAPİSİ(FAKÜLTE)'),
(92, 'DİL VE KONUŞMA TERAPİSİ(YÜKSEKOKUL)'),
(93, 'DİLBİLİMİ'),
(94, 'DİŞ HEKİMLİĞİ'),
(95, 'DOĞU DİLLERİ'),
(96, 'EBELİK(FAKÜLTE)'),
(97, 'EBELİK(YÜKSEKOKUL)'),
(98, 'ECZACILIK'),
(99, 'EGZERSİZ VE SPOR BİLİMLERİ'),
(100, 'EGZERSİZ VE SPOR BİLİMLERİ(AÇIKÖĞRETİM)'),
(101, 'EKONOMETRİ'),
(102, 'EKONOMİ'),
(103, 'EKONOMİ VE FİNANS'),
(104, 'EL SANATLARI'),
(105, 'EL SANATLARI(M.T.O.K.)'),
(106, 'ELEKTRİK MÜHENDİSLİĞİ'),
(107, 'ELEKTRİK-ELEKTRONİK MÜHENDİSLİĞİ'),
(108, 'ELEKTRİK-ELEKTRONİK MÜHENDİSLİĞİ(M.T.O.K.)'),
(109, 'ELEKTRONİK MÜHENDİSLİĞİ'),
(110, 'ELEKTRONİK TİCARET VE YÖNETİMİ'),
(111, 'ELEKTRONİK VE HABERLEŞME MÜHENDİSLİĞİ'),
(112, 'ELEKTRONİK VE HABERLEŞME MÜHENDİSLİĞİ(M.T.O.K.)'),
(113, 'ENDÜSTRİ MÜHENDİSLİĞİ'),
(114, 'ENDÜSTRİYEL TASARIM'),
(115, 'ENDÜSTRİYEL TASARIM'),
(116, 'ENDÜSTRİYEL TASARIM MÜHENDİSLİĞİ'),
(117, 'ENDÜSTRİYEL TASARIM MÜHENDİSLİĞİ(M.T.O.K.)'),
(118, 'ENERJİ MÜHENDİSLİĞİ'),
(119, 'ENERJİ SİSTEMLERİ MÜHENDİSLİĞİ'),
(120, 'ENERJİ SİSTEMLERİ MÜHENDİSLİĞİ(M.T.O.K.)'),
(121, 'ENERJİ YÖNETİMİ'),
(122, 'ERGOTERAPİ(FAKÜLTE)'),
(123, 'ERGOTERAPİ(YÜKSEKOKUL)'),
(124, 'ERMENİ DİLİ VE KÜLTÜRÜ(ERMENİCE)'),
(125, 'ESKİ YUNAN DİLİ VE EDEBİYATI'),
(126, 'FARS DİLİ VE EDEBİYATI'),
(127, 'FARS DİLİ VE EDEBİYATI'),
(128, 'FELSEFE'),
(129, 'FELSEFE(AÇIKÖĞRETİM)'),
(130, 'FELSEFE GRUBU ÖĞRETMENLİĞİ'),
(131, 'FEN BİLGİSİ ÖĞRETMENLİĞİ'),
(132, 'FİLM TASARIMI VE YAZARLIĞI'),
(133, 'FİLM TASARIMI YÖNETİMİ'),
(134, 'FİLM TASARIM VE YÖNETMENLİĞİ'),
(135, 'FİNANS VE BANKACILIK(FAKÜLTE)'),
(136, 'FİNANS VE BANKACILIK(YÜKSEKOKUL)'),
(137, 'FİZİK'),
(138, 'FİZİK MÜHENDİSLİĞİ'),
(139, 'FİZİK ÖĞRETMENLİĞİ'),
(140, 'FİZYOTERAPİ VE REHABİLİTASYON(FAKÜLTE)'),
(141, 'FİZYOTERAPİ VE REHABİLİTASYON(YÜKSEKOKUL)'),
(142, 'FOTOĞRAF'),
(143, 'FOTOĞRAF VE VİDEO'),
(144, 'FOTONİK'),
(145, 'FRANSIZ DİLİ VE EDEBİYATI'),
(146, 'FRANSIZCA MÜTERCİM VE TERCÜMANLIK'),
(147, 'FRANSIZCA ÖĞRETMENLİĞİ'),
(148, 'GASTRONOMİ VE MUTFAK SANATLARI(FAKÜLTE)'),
(149, 'GASTRONOMİ VE MUTFAK SANATLARI(FAKÜLTE)(M.T.O.K.)'),
(150, 'GASTRONOMİ VE MUTFAK SANATLARI(YÜKSEKOKUL)'),
(151, 'GAYRİMENKUL GELİŞTİRME VE YÖNETİMİ(FAKÜLTE)'),
(152, 'GAYRİMENKUL GELİŞTİRME VE YÖNETİMİ(YÜKSEKOKUL)'),
(153, 'GAZETECİLİK'),
(154, 'GELENEKSEL TÜRK SANATLARI(M.T.O.K.)'),
(155, 'GEMİ İNŞAATI VE GEMİ MAKİNELERİ MÜHENDİSLİĞİ'),
(156, 'GEMİ MAKİNELERİ İŞLETME MÜHENDİSLİĞİ(FAKÜLTE)'),
(157, 'GEMİ MAKİNELERİ İŞLETME MÜHENDİSLİĞİ(YÜKSEKOKUL)'),
(158, 'GEMİ VE DENİZ TEKNOLOJİSİ MÜHENDİSLİĞİ'),
(159, 'GEMİ VE YAT TASARIMI'),
(160, 'GENETİK VE BİYOMÜHENDİSLİK'),
(161, 'GENETİK VE YAŞAM BİLİMLERİ PROGRAMLARI'),
(162, 'GEOMATİK MÜHENDİSLİĞİ'),
(163, 'GERONTOLOJİ'),
(164, 'GIDA MÜHENDİSLİĞİ'),
(165, 'GIDA TEKNOLOJİSİ(FAKÜLTE)'),
(166, 'GIDA TEKNOLOJİSİ(YÜKSEKOKUL)'),
(167, 'GİRİŞİMCİLİK'),
(168, 'GÖRSEL İLETİŞİM TASARIMI'),
(169, 'GÖRSEL SANATLAR'),
(170, 'GÖRSEL SANATLAR(M.T.O.K.)'),
(171, 'GÖRSEL SANATLAR VE İLETİŞİM TASARIMI'),
(172, 'GRAFİK'),
(173, 'GRAFİK SANATLAR(AÇIKÖĞRETİM)'),
(174, 'GRAFİK TASARIMI'),
(175, 'GRAFİK TASARIMI(M.T.O.K.)'),
(176, 'GÜMRÜK İŞLETME(YÜKSEKOKUL)'),
(177, 'GÜRCÜ DİLİ VE EDEBİYATI'),
(178, 'HALKBİLİMİ'),
(179, 'HALKLA İLİŞKİLER'),
(180, 'HALKLA İLİŞKİLER VE REKLAMCILIK(AÇIKÖĞRETİM)'),
(181, 'HALKLA İLİŞKİLER VE REKLAMCILIK(FAKÜLTE)'),
(182, 'HALKLA İLİŞKİLER VE REKLAMCILIK(YÜKSEKOKUL)'),
(183, 'HALKLA İLİŞKİLER VE TANITIM'),
(184, 'HALKLA İLİŞKİLER VE TANITIM(AÇIKÖĞRETİM)'),
(185, 'HARİTA MÜHENDİSLİĞİ'),
(186, 'HAVACILIK ELEKTRİK VE ELEKTRONİĞİ(FAKÜLTE)'),
(187, 'HAVACILIK ELEKTRİK VE ELEKTRONİĞİ(YÜKSEKOKUL)'),
(188, 'HAVACILIK VE UZAY MÜHENDİSLİĞİ'),
(189, 'HAVACILIK YÖNETİMİ(AÇIKÖĞRETİM)'),
(190, 'HAVACILIK YÖNETİMİ(FAKÜLTE)'),
(191, 'HAVACILIK YÖNETİMİ(YÜKSEKOKUL)'),
(192, 'HAYVANSAL ÜRETİM VE TEKNOLOJİLERİ'),
(193, 'HEMŞİRELİK(FAKÜLTE)'),
(194, 'HEMŞİRELİK(YÜKSEKOKUL)'),
(195, 'HİDROJEOLOJİ MÜHENDİSLİĞİ'),
(196, 'HİNDOLOJİ'),
(197, 'HİTİTOLOJİ'),
(198, 'HUKUK'),
(199, 'HUNGAROLOJİ'),
(200, 'İBRANİ DİLİ VE EDEBİYATI'),
(201, 'İÇ MİMARLIK'),
(202, 'İÇ MİMARLIK VE ÇEVRE TASARIMI'),
(203, 'İKTİSADİ VE İDARİ BİLİMLER PROGRAMLARI'),
(204, 'İKTİSADİ VE İDARİ PROGRAMLARI'),
(205, 'İKTİSAT'),
(206, 'İKTİSAT(AÇIKÖĞRETİM)'),
(207, 'İLAHİYAT'),
(208, 'İLETİŞİM'),
(209, 'İLETİŞİM BİLİMLERİ'),
(210, 'İLETİŞİM SANATLARI'),
(211, 'İLETİŞİM TASARIMI VE YÖNETİMİ'),
(212, 'İLETİŞİM TASARIMI'),
(213, 'İLKÖĞRETİM MATEMATİK ÖĞRETMENLİĞİ'),
(214, 'İMALAT MÜHENDİSLİĞİ'),
(215, 'İNGİLİZ DİLBİLİMİ'),
(216, 'İNGİLİZ DİLİ VE EDEBİYATI'),
(217, 'İNGİLİZ VE RUS DİLLERİ VE EDEBİYATLARI'),
(218, 'İNGİLİZCE MÜTERCİM VE TERCÜMANLIK(FAKÜLTE)'),
(219, 'İNGİLİZCE MÜTERCİM VE TERCÜMANLIK(YÜKSEKOKUL)'),
(220, 'İNGİLİZCE ÖĞRETMENLİĞİ'),
(221, 'İNGİLİZCE, FRANSIZCA MÜTERCİM VE TERCÜMANLIK'),
(222, 'İNSAN KAYNAKLARI YÖNETİMİ(FAKÜLTE)(AÇIKÖĞRETİM)'),
(223, 'İNSAN KAYNAKLARI YÖNETİMİ(FAKÜLTE)'),
(224, 'İNSAN KAYNAKLARI YÖNETİMİ(YÜKSEKOKUL)'),
(225, 'İNŞAAT MÜHENDİSLİĞİ'),
(226, 'İNŞAAT MÜHENDİSLİĞİ(M.T.O.K.)'),
(227, 'İSLAM BİLİMLERİ'),
(228, 'İSLAM İKTİSADI VE FİNANS'),
(229, 'İSLAMİ İLİMLER'),
(230, 'İSPANYOL DİLİ VE EDEBİYATI'),
(231, 'İSTATİSTİK'),
(232, 'İSTATİSTİK VE BİLGİSAYAR BİLİMLERİ'),
(233, 'İŞ SAĞLIĞI VE GÜVENLİĞİ(AÇIKÖĞRETİM)'),
(234, 'İŞ SAĞLIĞI VE GÜVENLİĞİ(FAKÜLTE)'),
(235, 'İŞ SAĞLIĞI VE GÜVENLİĞİ(YÜKSEKOKUL)'),
(236, 'İŞLETME'),
(237, 'İŞLETME(AÇIKÖĞRETİM)'),
(238, 'İŞLETME MÜHENDİSLİĞİ'),
(239, 'İTALYAN DİLİ VE EDEBİYATI'),
(240, 'JAPON DİLİ VE EDEBİYATI'),
(241, 'JAPONCA ÖĞRETMENLİĞİ'),
(242, 'JEOFİZİK MÜHENDİSLİĞİ'),
(243, 'JEOLOJİ MÜHENDİSLİĞİ'),
(244, 'KAMU YÖNETİMİ'),
(245, 'KAMU YÖNETİMİ(AÇIKÖĞRETİM)'),
(246, 'KANATLI HAYVAN YETİŞTİRİCİLİĞİ'),
(247, 'KARŞILAŞTIRMALI EDEBİYAT'),
(248, 'KAZAK DİLİ VE EDEBİYATI'),
(249, 'KENTSEL TASARIM VE PEYZAJ MİMARLIĞI'),
(250, 'KİMYA'),
(251, 'KİMYA MÜHENDİSLİĞİ'),
(252, 'KİMYA ÖĞRETMENLİĞİ'),
(253, 'KİMYA-BİYOLOJİ MÜHENDİSLİĞİ'),
(254, 'KLASİK ARKEOLOJİ'),
(255, 'KONTROL VE OTOMASYON MÜHENDİSLİĞİ'),
(256, 'KORE DİLİ VE EDEBİYATI'),
(257, 'KURGU, SES VE GÖRÜNTÜ YÖNETİMİ'),
(258, 'KUYUMCULUK VE MÜCEVHER TASARIMI(FAKÜLTE)'),
(259, 'KUYUMCULUK VE MÜCEVHER TASARIMI(YÜKSEKOKUL)'),
(260, 'KÜLTÜR VARLIKLARINI KORUMA VE ONARIM'),
(261, 'KÜLTÜR VE İLETİŞİM BİLİMLERİ'),
(262, 'KÜRESEL SİYASET VE ULUSLARARASI İLİŞKİLER'),
(263, 'KÜRT DİLİ VE EDEBİYATI'),
(264, 'LATİN DİLİ VE EDEBİYATI'),
(265, 'LEH DİLİ VE EDEBİYATI'),
(266, 'LOJİSTİK YÖNETİMİ(FAKÜLTE)'),
(267, 'LOJİSTİK YÖNETİMİ(YÜKSEKOKUL)'),
(268, 'MADEN MÜHENDİSLİĞİ'),
(269, 'MAKİNE MÜHENDİSLİĞİ'),
(270, 'MAKİNE MÜHENDİSLİĞİ(M.T.O.K.)'),
(271, 'MALİYE'),
(272, 'MALİYE(AÇIKÖĞRETİM)'),
(273, 'MALZEME BİLİMİ VE MÜHENDİSLİĞİ'),
(274, 'MALZEME BİLİMİ VE NANOTEKNOLOJİ MÜHENDİSLİĞİ'),
(275, 'MALZEME BİLİMİ VE TEKNOLOJİLERİ'),
(276, 'MATEMATİK'),
(277, 'MATEMATİK MÜHENDİSLİĞİ'),
(278, 'MATEMATİK ÖĞRETMENLİĞİ'),
(279, 'MATEMATİK VE BİLGİSAYAR BİLİMLERİ'),
(280, 'MEDYA VE GÖRSEL SANATLAR'),
(281, 'MEDYA VE İLETİŞİM'),
(282, 'MEKATRONİK MÜHENDİSLİĞİ'),
(283, 'MEKATRONİK MÜHENDİSLİĞİ(M.T.O.K.)'),
(284, 'METALURJİ VE MALZEME MÜHENDİSLİĞİ'),
(285, 'METALURJİ VE MALZEME MÜHENDİSLİĞİ(M.T.O.K.)'),
(286, 'METEOROLOJİ MÜHENDİSLİĞİ'),
(287, 'MİMARLIK'),
(288, 'MODA TASARIMI(FAKÜLTE)'),
(289, 'MODA TASARIMI(FAKÜLTE)(M.T.O.K.)'),
(290, 'MODA TASARIMI(YÜKSEKOKUL)'),
(291, 'MOLEKÜLER BİYOLOJİ VE GENETİK'),
(292, 'MOLEKÜLER BİYOTEKNOLOJİ'),
(293, 'MUHASEBE VE DENETİM'),
(294, 'MUHASEBE VE FİNANS YÖNETİMİ(FAKÜLTE)'),
(295, 'MUHASEBE VE FİNANS YÖNETİMİ(YÜKSEKOKUL)'),
(296, 'MÜHENDİSLİK PROGRAMLARI'),
(297, 'MÜHENDİSLİK VE DOĞA BİLİMLERİ PROGRAMLARI'),
(298, 'MÜTERCİM-TERCÜMANLIK'),
(299, 'MÜZECİLİK'),
(300, 'NANOBİLİM VE NANOTEKNOLOJİ'),
(301, 'NANOTEKNOLOJİ MÜHENDİSLİĞİ'),
(302, 'NÜKLEER ENERJİ MÜHENDİSLİĞİ'),
(303, 'ODYOLOJİ(FAKÜLTE)'),
(304, 'ODYOLOJİ(YÜKSEKOKUL)'),
(305, 'OKUL ÖNCESİ ÖĞRETMENLİĞİ'),
(306, 'OPTİK VE AKUSTİK MÜHENDİSLİĞİ'),
(307, 'ORGANİK TARIM İŞLETMECİLİĞİ'),
(308, 'ORMAN ENDÜSTRİSİ MÜHENDİSLİĞİ'),
(309, 'ORMAN MÜHENDİSLİĞİ'),
(310, 'ORTEZ VE PROTEZ'),
(311, 'OTEL YÖNETİCİLİĞİ'),
(312, 'OTOMOTİV MÜHENDİSLİĞİ'),
(313, 'OTOMOTİV MÜHENDİSLİĞİ(M.T.O.K.)'),
(314, 'ÖZEL EĞİTİM ÖĞRETMENLİĞİ'),
(315, 'PAZARLAMA(FAKÜLTE)'),
(316, 'PAZARLAMA(YÜKSEKOKUL)'),
(317, 'PERFÜZYON(FAKÜLTE)'),
(318, 'PERFÜZYON(YÜKSEKOKUL)'),
(319, 'PETROL VE DOĞALGAZ MÜHENDİSLİĞİ'),
(320, 'PEYZAJ MİMARLIĞI'),
(321, 'PİLOTAJ(FAKÜLTE)'),
(322, 'PİLOTAJ(YÜKSEKOKUL)'),
(323, 'POLİMER MALZEME MÜHENDİSLİĞİ'),
(324, 'POLİTİKA VE EKONOMİ'),
(325, 'PROTOHİSTORYA VE ÖN ASYA ARKEOLOJİSİ'),
(326, 'PSİKOLOJİ'),
(327, 'PSİKOLOJİK DANIŞMANLIK VE REHBERLİK'),
(328, 'PSİKOLOJİK DANIŞMANLIK VE REHBERLİK ÖĞRETMENLİĞİ'),
(329, 'RADYO, TELEVİZYON VE SİNEMA'),
(330, 'RAYLI SİSTEMLER MÜHENDİSLİĞİ'),
(331, 'REHBERLİK VE PSİKOLOJİK DANIŞMANLIK'),
(332, 'REKLAM TASARIMI VE İLETİŞİMİ'),
(333, 'REKLAMCILIK'),
(334, 'REKLAMCILIK(AÇIKÖĞRETİM)'),
(335, 'REKREASYON(AÇIKÖĞRETİM)'),
(336, 'REKREASYON(FAKÜLTE)'),
(337, 'REKREASYON(YÜKSEKOKUL)'),
(338, 'REKREASYON YÖNETİMİ(FAKÜLTE)'),
(339, 'REKREASYON YÖNETİMİ(FAKÜLTE)(M.T.O.K.)'),
(340, 'REKREASYON YÖNETİMİ(YÜKSEKOKUL)'),
(341, 'RUS DİLİ VE EDEBİYATI'),
(342, 'RUS DİLİ VE EDEBİYATI ÖĞRETMENLİĞİ'),
(343, 'RUS VE İNGİLİZ DİLLERİ VE EDEBİYATLARI'),
(344, 'RUSÇA MÜTERCİM VE TERCÜMANLIK(FAKÜLTE)'),
(345, 'RUSÇA MÜTERCİM VE TERCÜMANLIK(YÜKSEKOKUL)'),
(346, 'SAĞLIK YÖNETİMİ(AÇIKÖĞRETİM)'),
(347, 'SAĞLIK YÖNETİMİ(FAKÜLTE)'),
(348, 'SAĞLIK YÖNETİMİ(YÜKSEKOKUL)'),
(349, 'SANAT TARİHİ'),
(350, 'SANAT VE KÜLTÜR YÖNETİMİ'),
(351, 'SANAT VE SOSYAL BİLİMLER PROGRAMLARI'),
(352, 'SERMAYE PİYASASI(FAKÜLTE)'),
(353, 'SERMAYE PİYASASI(YÜKSEKOKUL)'),
(354, 'SEYAHAT İŞLETMECİLİĞİ'),
(355, 'SEYAHAT İŞLETMECİLİĞİ VE TURİZM REHBERLİĞİ'),
(356, 'SINIF ÖĞRETMENLİĞİ'),
(357, 'SİGORTACILIK(FAKÜLTE)'),
(358, 'SİGORTACILIK(YÜKSEKOKUL)'),
(359, 'SİGORTACILIK VE AKTÜERYA BİLİMLERİ'),
(360, 'SİGORTACILIK VE RİSK YÖNETİMİ'),
(361, 'SİGORTACILIK VE SOSYAL GÜVENLİK'),
(362, 'SİNEMA VE DİJİTAL MEDYA'),
(363, 'SİNEMA VE TELEVİZYON'),
(364, 'SİNOLOJİ'),
(365, 'SİYASAL BİLİMLER'),
(366, 'SİYASET BİLİMİ'),
(367, 'SİYASET BİLİMİ VE KAMU YÖNETİMİ'),
(368, 'SİYASET BİLİMİ VE KAMU YÖNETİMİ(AÇIKÖĞRETİM)'),
(369, 'SİYASET BİLİMİ VE ULUSLARARASI İLİŞKİLER'),
(370, 'SİYASET BİLİMİ VE ULUSLARARASI İLİŞKİLER(AÇIKÖĞRETİM)'),
(371, 'SOSYAL BİLGİLER ÖĞRETMENLİĞİ'),
(372, 'SOSYAL HİZMET(FAKÜLTE)(AÇIKÖĞRETİM)'),
(373, 'SOSYAL HİZMET(FAKÜLTE)'),
(374, 'SOSYAL HİZMET(YÜKSEKOKUL)'),
(375, 'SOSYAL VE SİYASAL BİLİMLER'),
(376, 'SOSYOLOJİ'),
(377, 'SOSYOLOJİ(AÇIKÖĞRETİM)'),
(378, 'SPOR YÖNETİCİLİĞİ'),
(379, 'SPOR YÖNETİCİLİĞİ(FAKÜLTE)'),
(380, 'SPOR YÖNETİCİLİĞİ(YÜKSEKOKUL)'),
(381, 'SU BİLİMLERİ MÜHENDİSLİĞİ'),
(382, 'SU ÜRÜNLERİ MÜHENDİSLİĞİ'),
(383, 'SÜMEROLOJİ'),
(384, 'SÜRYANİ DİLİ VE EDEBİYATI'),
(385, 'SÜT TEKNOLOJİSİ'),
(386, 'ŞEHİR VE BÖLGE PLANLAMA'),
(387, 'TAKI TASARIMI'),
(388, 'TAPU KADASTRO'),
(389, 'TARIM EKONOMİSİ'),
(390, 'TARIM MAKİNELERİ VE TEKNOLOJİLERİ MÜHENDİSLİĞİ'),
(391, 'TARIM TİCARETİ VE İŞLETMECİLİĞİ'),
(392, 'TARIMSAL BİYOTEKNOLOJİ'),
(393, 'TARIMSAL GENETİK MÜHENDİSLİĞİ'),
(394, 'TARIMSAL YAPILAR VE SULAMA'),
(395, 'TARİH'),
(396, 'TARİH(AÇIKÖĞRETİM)'),
(397, 'TARİH ÖĞRETMENLİĞİ'),
(398, 'TARİH ÖNCESİ ARKEOLOJİSİ'),
(399, 'TARLA BİTKİLERİ'),
(400, 'TEKNOLOJİ VE BİLGİ YÖNETİMİ'),
(401, 'TEKSTİL MÜHENDİSLİĞİ'),
(402, 'TEKSTİL MÜHENDİSLİĞİ(M.T.O.K.)'),
(403, 'TEKSTİL TASARIMI'),
(404, 'TEKSTİL TASARIMI(M.T.O.K.)'),
(405, 'TEKSTİL VE MODA TASARIMI(AÇIKÖĞRETİM)'),
(406, 'TEKSTİL VE MODA TASARIMI(FAKÜLTE)'),
(407, 'TEKSTİL VE MODA TASARIMI(YÜKSEKOKUL)'),
(408, 'TELEVİZYON HABERCİLİĞİ VE PROGRAMCILIĞI(FAKÜLTE)'),
(409, 'TELEVİZYON HABERCİLİĞİ VE PROGRAMCILIĞI(YÜKSEKOKUL)'),
(410, 'TIP'),
(411, 'TIP MÜHENDİSLİĞİ'),
(412, 'TİYATRO ELEŞTİRMENLİĞİ VE DRAMATURJİ'),
(413, 'TOHUM BİLİMİ VE TEKNOLOJİSİ'),
(414, 'TOPRAK BİLİMİ VE BİTKİ BESLEME'),
(415, 'TURİZM İŞLETMECİLİĞİ(FAKÜLTE)'),
(416, 'TURİZM İŞLETMECİLİĞİ(YÜKSEKOKUL)'),
(417, 'TURİZM REHBERLİĞİ(FAKÜLTE)'),
(418, 'TURİZM REHBERLİĞİ(YÜKSEKOKUL)'),
(419, 'TURİZM VE OTEL İŞLETMECİLİĞİ'),
(420, 'TÜRK DİLİ VE EDEBİYATI'),
(421, 'TÜRK DİLİ VE EDEBİYATI(AÇIKÖĞRETİM)'),
(422, 'TÜRK DİLİ VE EDEBİYATI ÖĞRETMENLİĞİ'),
(423, 'TÜRK HALKBİLİMİ'),
(424, 'TÜRK İSLAM ARKEOLOJİSİ'),
(425, 'TÜRKÇE ÖĞRETMENLİĞİ'),
(426, 'TÜRKOLOJİ'),
(427, 'TÜTÜN EKSPERLİĞİ'),
(428, 'UÇAK BAKIM VE ONARIM(FAKÜLTE)'),
(429, 'UÇAK BAKIM VE ONARIM(YÜKSEKOKUL)'),
(430, 'UÇAK ELEKTRİK VE ELEKTRONİĞİ'),
(431, 'UÇAK GÖVDE VE MOTOR BAKIMI(FAKÜLTE)'),
(432, 'UÇAK GÖVDE VE MOTOR BAKIMI(YÜKSEKOKUL)'),
(433, 'UÇAK MÜHENDİSLİĞİ'),
(434, 'UKRAYNA DİLİ VE EDEBİYATI'),
(435, 'ULUSLARARASI EKONOMİK İLİŞKİLER'),
(436, 'ULUSLARARASI FİNANS'),
(437, 'ULUSLARARASI FİNANS VE BANKACILIK'),
(438, 'ULUSLARARASI GİRİŞİMCİLİK'),
(439, 'ULUSLARARASI İLİŞKİLER'),
(440, 'ULUSLARARASI İLİŞKİLER(AÇIKÖĞRETİM)'),
(441, 'ULUSLARARASI İŞLETME YÖNETİMİ'),
(442, 'ULUSLARARASI TIP'),
(443, 'ULUSLARARASI TİCARET(FAKÜLTE)'),
(444, 'ULUSLARARASI TİCARET(YÜKSEKOKUL)'),
(445, 'ULUSLARARASI TİCARET VE FİNANS'),
(446, 'ULUSLARARASI TİCARET VE FİNANSMAN(FAKÜLTE)'),
(447, 'ULUSLARARASI TİCARET VE FİNANSMAN(YÜKSEKOKUL)'),
(448, 'ULUSLARARASI TİCARET VE İŞLETMECİLİK(FAKÜLTE)'),
(449, 'ULUSLARARASI TİCARET VE İŞLETMECİLİK (YÜKSEKOKUL)'),
(450, 'ULUSLARARASI TİCARET VE LOJİSTİK(AÇIKÖĞRETİM))'),
(451, 'ULUSLARARASI TİCARET VE LOJİSTİK(FAKÜLTE)'),
(452, 'ULUSLARARASI TİCARET VE LOJİSTİK(YÜKSEKOKUL)'),
(453, 'ULUSLARARASI ULAŞTIRMA SİSTEMLERİ'),
(454, 'URDU DİLİ VE EDEBİYATI'),
(455, 'UZAY BİLİMLERİ VE TEKNOLOJİLERİ'),
(456, 'UZAY MÜHENDİSLİĞİ'),
(457, 'UZAY VE UYDU MÜHENDİSLİĞİ'),
(458, 'VETERİNERLİK'),
(459, 'YABAN HAYATI EKOLOJİSİ VE YÖNETİMİ'),
(460, 'YAPAY ZEKA MÜHENDİSLİĞİ'),
(461, 'YAPAY ZEKA VE VERİ MÜHENDİSLİĞİ'),
(462, 'YAZILIM GELİŞTİRME'),
(463, 'YAZILIM MÜHENDİSLİĞİ'),
(464, 'YAZILIM MÜHENDİSLİĞİ(M.T.O.K.)'),
(465, 'YENİ MEDYA(FAKÜLTE)'),
(466, 'YENİ MEDYA(YÜKSEKOKUL)'),
(467, 'YENİ MEDYA VE İLETİŞİM'),
(468, 'YEREL YÖNETİMLER'),
(469, 'YİYECEK VE İÇECEK İŞLETMECİLİĞİ'),
(470, 'YÖNETİM BİLİMLERİ PROGRAMLARI'),
(471, 'YÖNETİM BİLİŞİM SİSTEMLERİ(FAKÜLTE)(AÇIKÖĞRETİM)'),
(472, 'YÖNETİM BİLİŞİM SİSTEMLERİ(FAKÜLTE)'),
(473, 'YÖNETİM BİLİŞİM SİSTEMLERİ(YÜKSEKOKUL)'),
(474, 'YUNAN DİLİ VE EDEBİYATI'),
(475, 'ZAZA DİLİ VE EDEBİYATI'),
(476, 'ZİRAAT MÜHENDİSLİĞİ PROGRAMLARI'),
(477, 'ZOOTEKNİ') ON CONFLICT DO NOTHING;


INSERT INTO job_positions (id, position_name) VALUES (1, 'Acentacı'),
       (2, 'Acil durum yönetmeni'),
       (3, 'Adli Tabip'),
       (4, 'Agronomist'),
       (5, 'Ağ yöneticisi'),
       (6, 'Aşçı'),
       (7, 'Aşçıbaşı'),
       (8, 'Ahşap tekne yapımcısı'),
       (9, 'Aile hekimi'),
       (10, 'Akortçu'),
       (11, 'Aktör'),
       (12, 'Aktris'),
       (13, 'Akustikçi'),
       (14, 'Albay'),
       (15, 'Ambalajcı'),
       (16, 'Ambarcı'),
       (17, 'Ambulans şoförü'),
       (18, 'Amiral'),
       (19, 'Anahtarcı'),
       (20, 'Analist'),
       (21, 'Anestezi uzmanı'),
       (22, 'Animatör'),
       (23, 'Antika satıcısı'),
       (24, 'Antropolog'),
       (25, 'Apartman yöneticisi'),
       (26, 'Araba satıcısı'),
       (27, 'Araba yıkayıcısı'),
       (28, 'Arabacı'),
       (29, 'Arabulucu'),
       (30, 'Araştırmacı'),
       (31, 'Arıcı'),
       (32, 'Arkeolog'),
       (33, 'Armatör'),
       (34, 'Arşivci'),
       (35, 'Artist'),
       (36, 'Asansörcü'),
       (37, 'Asistan'),
       (38, 'Asker'),
       (39, 'Astrofizikçi'),
       (40, 'Astrolog'),
       (41, 'Astronom'),
       (42, 'Astronot'),
       (43, 'Atlet'),
       (44, 'Av bekçisi'),
       (45, 'Avcı'),
       (46, 'Avizeci'),
       (47, 'Avukat'),
       (48, 'Ayakçı (otogar, lokanta)'),
       (49, 'Ayakkabı boyacısı'),
       (50, 'Ayakkabı tamircisi'),
       (51, 'Ayakkabıcı'),
       (52, 'Ayı oynatıcısı'),
       (53, 'Avukat'),
       (54, 'Bacacı'),
       (55, 'Badanacı'),
       (56, 'Baharatçı'),
       (57, 'Bahçe bitkileri uzmanı'),
       (58, 'Bahçıvan'),
       (59, 'Bakıcı'),
       (60, 'Bakırcı'),
       (61, 'Bakkal'),
       (62, 'Bakteriyolog'),
       (63, 'Balıkçı'),
       (64, 'Balon pilotu'),
       (65, 'Bankacı'),
       (66, 'Banker'),
       (67, 'Barmen'),
       (68, 'Barmeyd'),
       (69, 'Basketbolcu'),
       (70, 'Başbakan'),
       (71, 'Başçavuş'),
       (72, 'Başdümenci'),
       (73, 'Başhemşire'),
       (74, 'Başkan'),
       (75, 'Başkomiser'),
       (76, 'Başpiskopos'),
       (77, 'Başrahip'),
       (78, 'Belediye başkanı'),
       (79, 'Belediye meclisi üyesi'),
       (80, 'Benzinci'),
       (81, 'Berber'),
       (82, 'Besteci'),
       (83, 'Biletçi'),
       (84, 'Bilgisayar mühendisi'),
       (85, 'Bilgisayar programcısı'),
       (86, 'Bilgisayar tamircisi'),
       (87, 'Bilimadamı'),
       (88, 'Bilirkişi'),
       (89, 'Biracı'),
       (90, 'Bisikletçi'),
       (91, 'Biyografi yazarı'),
       (92, 'Biyolog'),
       (93, 'Bobinajcı'),
       (94, 'Bombacı'),
       (95, 'Bomba imhacı'),
       (96, 'Borsacı'),
       (97, 'Borucu'),
       (98, 'Botanikçi'),
       (99, 'Boyacı'),
       (100, 'Bozacı'),
       (101, 'Böcekbilimci'),
       (102, 'Börekçi'),
       (103, 'Bulaşıkçı'),
       (104, 'Buldozer operatörü'),
       (105, 'Bütçe uzmanı'),
       (106, 'Büyükelçi'),
       (107, 'Besicilik'),
       (108, 'Camcı'),
       (109, 'Cerrah'),
       (110, 'Celep'),
       (111, 'Cellat'),
       (112, 'Cost Control'),
       (113, 'Cillopçu'),
       (114, 'Çamaşırcı'),
       (115, 'Çantacı'),
       (116, 'Çarkçı'),
       (117, 'Çatıcı'),
       (118, 'Çaycı'),
       (119, 'Çevirmen'),
       (120, 'Çevrebilimci'),
       (121, 'Çeyizci'),
       (122, 'Çıkıkçı'),
       (123, 'Çıkrıkçı'),
       (124, 'Çiçekçi'),
       (125, 'Çiftçi'),
       (126, 'Çiftlik işletici'),
       (127, 'Çikolatacı'),
       (128, 'Çilingir'),
       (129, 'Çinici'),
       (130, 'Çitçi'),
       (131, 'Çoban'),
       (132, 'Çocuk doktoru'),
       (133, 'Çorapçı'),
       (134, 'Çöp işçisi'),
       (135, 'Çöpçü'),
       (136, 'Çırak'),
       (137, 'Dadı'),
       (138, 'Daktilograf'),
       (139, 'Dalgıç'),
       (140, 'Damıtıcı'),
       (141, 'Danışman'),
       (142, 'Dansöz'),
       (143, 'Davulcu'),
       (144, 'Debbağ'),
       (145, 'Dedektif'),
       (146, 'Değirmen işçisi'),
       (147, 'Değirmenci'),
       (148, 'Demirci'),
       (149, 'Demiryolu işçisi'),
       (150, 'Denetçi'),
       (151, 'Denetleyici'),
       (152, 'Denizci'),
       (153, 'Depocu'),
       (154, 'Derici'),
       (155, 'Desinatör'),
       (156, 'Devlet memuru'),
       (157, 'Dilci'),
       (158, 'Dilenci'),
       (159, 'Diplomat'),
       (160, 'Diş hekimi'),
       (161, 'Diyetisten'),
       (162, 'Dizgici'),
       (163, 'Doğalgazcı'),
       (164, 'Doğramacı'),
       (165, 'Doğum uzmanı'),
       (166, 'Dok işçisi'),
       (167, 'Dokmacı'),
       (168, 'Doktor'),
       (169, 'Dondurmacı'),
       (170, 'Dökümcü'),
       (171, 'Döşemeci'),
       (172, 'Dövizci'),
       (173, 'Dublajcı'),
       (174, 'Duvarcı'),
       (175, 'Dümenci'),
       (176, 'Ebe'),
       (177, 'Eczacı'),
       (178, 'Eczacı kalfası'),
       (179, 'Editör'),
       (180, 'Eğitimci'),
       (181, 'Eğitmen'),
       (182, 'Ekonomist'),
       (183, 'Elektrik mühendisi'),
       (184, 'Elektronik mühendisi'),
       (185, 'Elektrikçi'),
       (186, 'Eleştirmen'),
       (187, 'Embiriyolog'),
       (188, 'Emlakçı'),
       (189, 'Emniyet amiri'),
       (190, 'Emniyet Genel Müdürü'),
       (191, 'Endüstri mühendisi'),
       (192, 'Endüstri sistemleri mühendisi'),
       (193, 'Enstrüman imalatçısı'),
       (194, 'Ergonomist'),
       (195, 'Eskici'),
       (196, 'Estetisyen'),
       (197, 'Etolojist'),
       (198, 'Etimolog'),
       (199, 'Etnolog'),
       (200, 'Ev hanımı'),
       (201, 'Fabrika işçisi'),
       (202, 'Falcı'),
       (203, 'Fermantasyon işçisi'),
       (204, 'Fıçıcı'),
       (205, 'Fırıncı'),
       (206, 'Figüran'),
       (207, 'Film yapımcısı'),
       (208, 'Film yönetmeni'),
       (209, 'Filozof'),
       (210, 'Finansör'),
       (211, 'Fizikçi'),
       (212, 'Fizyonomist'),
       (213, 'Fizyoterapist'),
       (214, 'Flütçü'),
       (215, 'Fon yöneticisi'),
       (216, 'Forklift operatörü'),
       (217, 'Fotoğrafçı'),
       (218, 'Futbolcu'),
       (219, 'Gardiyan'),
       (220, 'Galerici'),
       (221, 'Garson'),
       (222, 'Gazete dağıtıcısı'),
       (223, 'Gazete satıcısı'),
       (224, 'Gazeteci'),
       (225, 'Gemici'),
       (226, 'General'),
       (227, 'Genetik mühendisi'),
       (228, 'Geyşa'),
       (229, 'Gezgin'),
       (230, 'Gezici vaiz'),
       (231, 'Gıda Mühendisi'),
       (232, 'Gitarist'),
       (233, 'Gondolcu'),
       (234, 'Gökbilimci'),
       (235, 'Göz doktoru'),
       (236, 'Gözetmen'),
       (237, 'Gözlükçü'),
       (238, 'Grafiker'),
       (239, 'Gramer uzmanı'),
       (240, 'Greyder operatörü'),
       (241, 'Guru'),
       (242, 'Güfteci'),
       (243, 'Gümrük memuru'),
       (244, 'Gümrük uzmanı'),
       (245, 'Gündelikçi'),
       (246, 'Güzellik uzmanı'),
       (247, 'Haberci'),
       (248, 'Haddeci'),
       (249, 'Haham'),
       (250, 'Hakem'),
       (251, 'Halıcı'),
       (252, 'Halkbilimci'),
       (253, 'Hamal'),
       (254, 'Hamamcı'),
       (255, 'Hamurkar'),
       (256, 'Hareket memuru'),
       (257, 'Haritacı'),
       (258, 'Harita mühendisi'),
       (259, 'Harp çalıcı'),
       (260, 'Hastabakıcı'),
       (261, 'Hattat'),
       (262, 'Hava trafikçisi'),
       (263, 'Havacı'),
       (264, 'Haydut'),
       (265, 'Hayvan bakıcısı'),
       (266, 'Hayvan terbiyecisi'),
       (267, 'Hemşire'),
       (268, 'Hesap uzmanı'),
       (269, 'Heykeltraş'),
       (270, 'Hırdavatçı'),
       (271, 'Hırsız'),
       (272, 'Hidrolikçi'),
       (273, 'Hizmetçi'),
       (274, 'Hokkabaz'),
       (275, 'Host'),
       (276, 'Hostes'),
       (277, 'Hukukçu'),
       (278, 'Irgat'),
       (279, 'Işıkçı'),
       (280, 'İcra memuru'),
       (281, 'İç mimar'),
       (282, 'İğneci'),
       (283, 'İhracatçı'),
       (284, 'İktisatçı'),
       (285, 'İlahiyatçı'),
       (286, 'İllüzyonist'),
       (287, 'İmam'),
       (288, 'İnsan kaynakları uzmanı'),
       (289, 'İnşaat mühendisi'),
       (290, 'İnşaatçı'),
       (291, 'İpçi'),
       (292, 'İplikçi'),
       (293, 'İstatistikçi'),
       (294, 'İstihkamcı'),
       (295, 'İşaretçi'),
       (296, 'İşçi'),
       (297, 'İşletmeci'),
       (298, 'İşletme mühendisi'),
       (299, 'İşportacı'),
       (300, 'İtfaiyeci'),
       (301, 'İthalatçı'),
       (302, 'Jeoloji mühendisi'),
       (303, 'Jeolog'),
       (304, 'Jeomorfolog'),
       (305, 'Jigolo'),
       (306, 'Jinekolog'),
       (307, 'Jimnastikçi'),
       (308, 'Jokey'),
       (309, 'Kabin görevlisi'),
       (310, 'Kabuk soyucusu'),
       (311, 'Kadın berberi'),
       (312, 'Kadın terzisi'),
       (313, 'Kâğıtçı'),
       (314, 'Kahveci'),
       (315, 'Kahya'),
       (316, 'Kalaycı'),
       (317, 'Kalıpçı'),
       (318, 'Kaloriferci'),
       (319, 'Kamarot'),
       (320, 'Kameraman'),
       (321, 'Kamyoncu'),
       (322, 'Kapı satıcısı'),
       (323, 'Kapıcı'),
       (324, 'Kaplamacı'),
       (325, 'Kaportacı'),
       (326, 'Kaptan'),
       (327, 'Kardinal'),
       (328, 'Kardiyolog'),
       (329, 'Karikatürist'),
       (330, 'Karoserci'),
       (331, 'Karpuzcu'),
       (332, 'Kasap'),
       (333, 'Kasiyer'),
       (334, 'Kat görevlisi'),
       (335, 'Katip'),
       (336, 'Kayıkçı'),
       (337, 'Kaymakam'),
       (338, 'Kaynakçı'),
       (339, 'Kazıcı'),
       (340, 'Kebapçı'),
       (341, 'Kemancı'),
       (342, 'Kesimci'),
       (343, 'Kırtasiyeci'),
       (344, 'Kimyager'),
       (345, 'Kimya mühendisi'),
       (346, 'Kitapçı'),
       (347, 'Klarnetçi'),
       (348, 'Koleksiyoncu'),
       (349, 'Komedyen'),
       (350, 'Komisyoncu'),
       (351, 'Konserveci'),
       (352, 'Konsolos'),
       (353, 'Konsomatris'),
       (354, 'Kontrolör'),
       (355, 'Konveyör operatörü'),
       (356, 'Kopyalayıcı'),
       (357, 'Koreograf'),
       (358, 'Korgeneral'),
       (359, 'Koramiral'),
       (360, 'Korsan'),
       (361, 'Koruma görevlisi'),
       (362, 'Komiser'),
       (363, 'Komiser Yardımcısı'),
       (364, 'Kaval'),
       (365, 'Köfteci'),
       (366, 'Kömürcü'),
       (367, 'Köpek eğiticisi'),
       (368, 'Köşeyazarı'),
       (369, 'Kuaför'),
       (370, 'Kuşçu'),
       (371, 'Kumarbaz'),
       (372, 'Kumaşçı'),
       (373, 'Kumcu'),
       (374, 'Kuru temizlemeci'),
       (375, 'Kuruyemişçi'),
       (376, 'Kurye'),
       (377, 'Kuşbilimci (veya ornitolog)'),
       (378, 'Kuyumcu'),
       (379, 'Kürkçü'),
       (380, 'Kütüphaneci'),
       (381, 'Laborant'),
       (382, 'Laboratuar işçisi'),
       (383, 'Lahmacuncu'),
       (384, 'Lehimci'),
       (385, 'Levazımcı'),
       (386, 'Lobici'),
       (387, 'Lokantacı'),
       (388, 'Lokomotifçi'),
       (389, 'Lostromo'),
       (390, 'Lostracı'),
       (391, 'Lokman'),
       (392, 'Madencil'),
       (393, 'Makasçi'),
       (394, 'Makastar'),
       (395, 'Makinist'),
       (396, 'Makina zabiti'),
       (397, 'Makyajci'),
       (398, 'Mali Hizmetler Uzmanı'),
       (399, 'Manastir basrahibesi'),
       (400, 'Manav'),
       (401, 'Manifaturaci'),
       (402, 'Manikürcü'),
       (403, 'Manken'),
       (404, 'Marangoz'),
       (405, 'Masör'),
       (406, 'Masöz'),
       (407, 'Matador'),
       (408, 'Matbaaci'),
       (409, 'Matematikçi'),
       (410, 'Matkapçi'),
       (411, 'Memur'),
       (412, 'Menajer'),
       (413, 'Mermerci'),
       (414, 'Meteoroloji uzmani'),
       (415, 'Metin yazari'),
       (416, 'Mevsimlik isçi'),
       (417, 'Meydanci'),
       (418, 'Meyhaneci'),
       (419, 'Mezarci'),
       (420, 'Midyeci'),
       (421, 'Mikrobiyolog'),
       (422, 'Milletvekili'),
       (423, 'Mimar'),
       (424, 'Misyoner'),
       (425, 'Mobilyaci'),
       (426, 'Modacı'),
       (427, 'Model'),
       (428, 'Modelist'),
       (429, 'Montajci'),
       (430, 'Montör'),
       (431, 'Motor tamircisi'),
       (432, 'Motorcu'),
       (433, 'Muallim'),
       (434, 'Muhabir'),
       (435, 'Muhafiz'),
       (436, 'Muhasebeci'),
       (437, 'Muhtar'),
       (438, 'Mumyalayici'),
       (439, 'Muzcu'),
       (440, 'mübasir'),
       (441, 'Müdür'),
       (442, 'müezzin'),
       (443, 'Müfettis'),
       (444, 'Müşavir'),
       (445, 'Mühendis'),
       (446, 'Müneccim'),
       (447, 'Mürebbiye'),
       (448, 'Müstesar'),
       (449, 'müteahhit'),
       (450, 'Müze müdürü'),
       (451, 'Müzik yönetmeni'),
       (452, 'Müzisyen'),
       (453, 'Nalıncı'),
       (454, 'Nakışçı'),
       (455, 'Nakliyeci'),
       (456, 'Nalbant'),
       (457, 'Nalbur'),
       (458, 'Noter'),
       (459, ' O [değiştir]Obuacı'),
       (460, 'Ocakçı'),
       (461, 'Odacı'),
       (462, 'Oduncu'),
       (463, 'Okçu'),
       (464, 'Okul müdürü'),
       (465, 'Okutman'),
       (466, 'Operatör'),
       (467, 'Opera sanatçısı'),
       (468, 'Orgcu'),
       (469, 'Orgeneral'),
       (470, 'Orman mühendisi'),
       (471, 'Ornitolog (veya kuşbilimci)'),
       (472, 'Otelci'),
       (473, 'Oto elektrikçisi'),
       (474, 'Oto tamircisi'),
       (475, 'Oto yedek parçacı'),
       (476, 'Overlokçu'),
       (477, 'Oymacı'),
       (478, 'Oyuncu'),
       (479, 'Oyun hostesi'),
       (480, 'Oyun yazarı'),
       (481, 'Oto lastik tamircisi'),
       (482, 'Öğretmen'),
       (483, 'Öğretim elemanı'),
       (484, 'Öğretim görevlisi'),
       (485, 'Öğretim üyesi'),
       (486, 'Örmeci'),
       (487, 'Ön Muhasebeci'),
       (488, 'Ön Muhasebe Sorumlusu'),
       (489, 'Ön Muhasebe Yardımcı Elemanı'),
       (490, 'Ön Büro Elemanı'),
       (491, 'Özel Şoför'),
       (492, 'Paketleyici'),
       (493, 'Palyaço'),
       (494, 'Pandomimci'),
       (495, 'Pansiyoncu'),
       (496, 'Pansumanci'),
       (497, 'Papa'),
       (498, 'Papaz'),
       (499, 'Paralı asker'),
       (500, 'Park bekçisi'),
       (501, 'Pastörizör'),
       (502, 'Patolog'),
       (503, 'Peçeteci'),
       (504, 'Pencereci'),
       (505, 'Perukçu'),
       (506, 'Peyzaj Mimarlığı'),
       (507, 'Pideci'),
       (508, 'Pilavci'),
       (509, 'Pilot'),
       (510, 'Piskopos'),
       (511, 'Piyade'),
       (512, 'Piyango saticisi'),
       (513, 'Piyanist'),
       (514, 'Polis memuru'),
       (515, 'Polis sefi'),
       (516, 'Polisajci'),
       (517, 'Politikaci'),
       (518, 'Pompaci'),
       (519, 'Postaci'),
       (520, 'Profesör'),
       (521, 'Proktolog'),
       (522, 'Protokol görevlisi'),
       (523, 'Psikiyatr'),
       (524, 'Psikolog'),
       (525, 'Psikolojik danışmanlık ve rehberlik'),
       (526, 'Radyolog'),
       (527, 'Redaktör'),
       (528, 'Rehber'),
       (529, 'Rejisör'),
       (530, 'Reklamcı'),
       (531, 'Rektör'),
       (532, 'Rektör yardımcısı'),
       (533, 'Remayözcü'),
       (534, 'Ressam'),
       (535, 'Resepsiyon memuru'),
       (536, 'Rot balansçı'),
       (537, 'Saat tamircisi'),
       (538, 'Saatçi'),
       (539, 'Sahil koruma'),
       (540, 'Saksofoncu'),
       (541, 'Salepçi'),
       (542, 'Sanat yönetmeni'),
       (543, 'Sanayici'),
       (544, 'Sansürcü'),
       (545, 'Santral memuru'),
       (546, 'Saraç'),
       (547, 'Sarraf'),
       (548, 'Satis elemani'),
       (549, 'Savci'),
       (550, 'Saz sairi'),
       (551, 'Sekreter'),
       (552, 'Sepetçi'),
       (553, 'Serbest Muhasebeci Mali Müşavir'),
       (554, 'Ses teknisyeni'),
       (555, 'Seyis'),
       (556, 'Sinirli basmakinist'),
       (557, 'Sicil memuru'),
       (558, 'Sigortaci'),
       (559, 'Sihirbaz'),
       (560, 'Silahçi'),
       (561, 'Silindir operatörü'),
       (562, 'Simitçi'),
       (563, 'Simyaci'),
       (564, 'Sistem Mühendisi'),
       (565, 'Sistem yöneticisi'),
       (566, 'Soguk demirci'),
       (567, 'Sokak çalgicisi'),
       (568, 'Sokak saticisi'),
       (569, 'Sorgu hakimi'),
       (570, 'Sosyal hizmet uzmanı'),
       (571, 'Spiker'),
       (572, 'Stenograf'),
       (573, 'Stilist'),
       (574, 'Striptizci'),
       (575, 'Su tesisatçisi'),
       (576, 'Sucu'),
       (577, 'Suflör'),
       (578, 'Sulh hakimi'),
       (579, 'Sunucu'),
       (580, 'Susuz Araç Yıkama'),
       (581, 'Sünnetçi'),
       (582, 'Sürveyan'),
       (583, 'Sütanne'),
       (584, 'Sütçü'),
       (585, 'Şahinci'),
       (586, 'Şair'),
       (587, 'Şapel papazı'),
       (588, 'Şapkacı'),
       (589, 'Şarap üreticisi'),
       (590, 'Şarkıcı'),
       (591, 'Şarkı sözü yazarı'),
       (592, 'Şarküter'),
       (593, 'Şekerci'),
       (594, 'Şemsiyeci'),
       (595, 'Şifre çözümleyici'),
       (596, 'Şimşirci'),
       (597, 'Şoför'),
       (598, 'Tabakçi'),
       (599, 'Tabelaci'),
       (600, 'Tahsildar'),
       (601, 'Taksici'),
       (602, 'Tarim isçisi'),
       (603, 'Tarihçi'),
       (604, 'Tasarimci'),
       (605, 'Tasçi'),
       (606, 'Taslayici'),
       (607, 'Tatlıcı'),
       (608, 'Tavukçu'),
       (609, 'Tayfa'),
       (610, 'Tefeci'),
       (611, 'Tegmen'),
       (612, 'Tekniker'),
       (613, 'Teknisyen'),
       (614, 'Teknoloji uzmani'),
       (615, 'Telefon operatörü'),
       (616, 'Telekiz'),
       (617, 'Televizyon tamircisi'),
       (618, 'Tellal'),
       (619, 'Temizlikçi'),
       (620, 'Temsilci'),
       (621, 'Terapist'),
       (622, 'Tercüman'),
       (623, 'Terzi'),
       (624, 'Tesgahtar'),
       (625, 'Tesisatçi'),
       (626, 'Tesviyeci'),
       (627, 'Test Mühendisi'),
       (628, 'Test pilotu'),
       (629, 'Tesrifatçi'),
       (630, 'Tiyatro yönetmeni'),
       (631, 'Tombalaci'),
       (632, 'Topçu'),
       (633, 'Tornaci'),
       (634, 'turizimci'),
       (635, 'Tuggeneral'),
       (636, 'Tuhafiyeci'),
       (637, 'Tursucu'),
       (638, 'Tuzcu'),
       (639, 'Tümamiral'),
       (640, 'Tümgeneral'),
       (641, 'Uçuş teknisyeni'),
       (642, 'Ulaşım sorumlusu'),
       (643, 'Ustabaşı'),
       (644, 'Uydu antenci'),
       (645, 'Uzay mühendisi'),
       (646, 'Üretici'),
       (647, 'Ürolog'),
       (648, 'Ütücü'),
       (649, 'Vaiz'),
       (650, 'Vali'),
       (651, 'Vergi denetmeni'),
       (652, 'Vergi tahakkuk memuru'),
       (653, 'Veritabanı yöneticisi'),
       (654, 'Veri hazırlama ve kontrol işletmeni'),
       (655, 'Vestiyer'),
       (656, 'Veteriner'),
       (657, 'Veznedar'),
       (658, 'Video editörü'),
       (659, 'Vinç operatörü'),
       (660, 'Vitrinci'),
       (661, 'Viyolonselci'),
       (662, 'Yarbay'),
       (663, 'Yardımcı hakem'),
       (664, 'Yardımcı hizmetli'),
       (665, 'Yardımcı pilot'),
       (666, 'Yargıç'),
       (667, 'Yatırım uzmanı'),
       (668, 'Yayıncı'),
       (669, 'Yazar'),
       (670, 'Yazı işleri müdürü'),
       (671, 'Yazılım mühendisi'),
       (672, 'Yelkenci'),
       (673, 'Yeminli mali müşavir'),
       (674, 'Yeminli tercüman'),
       (675, 'Yer gösterici'),
       (676, 'Yer teknisyeni'),
       (677, 'Yerölçmeci'),
       (678, 'Yoğurtçu'),
       (679, 'Yol bekçisi'),
       (680, 'Yorgancı'),
       (681, 'Yorumcu'),
       (682, 'Yönetici'),
       (683, 'Yüzücü'),
       (684, 'yönetmen'),
       (685, 'Zabıta'),
       (686, 'Zoolog') ON CONFLICT DO NOTHING;
	   
INSERT INTO cities (id, city_name) VALUES (1,'ADANA'),
(2,'ADIYAMAN'),
(3,'AFYONKARAHİSAR'),
(4,'AĞRI'),
(5,'AMASYA'),
(6,'ANKARA'),
(7,'ANTALYA'),
(8,'ARTVİN'),
(9,'AYDIN'),
(10,'BALIKESİR'),
(11,'BİLECİK'),
(12,'BİNGÖL'),
(13,'BİTLİS'),
(14,'BOLU'),
(15,'BURDUR'),
(16,'BURSA'),
(17,'ÇANAKKALE'),
(18,'ÇANKIRI'),
(19,'ÇORUM'),
(20,'DENİZLİ'),
(21,'DİYARBAKIR'),
(22,'EDİRNE'),
(23,'ELAZIĞ'),
(24,'ERZİNCAN'),
(25,'ERZURUM'),
(26,'ESKİŞEHİR'),
(27,'GAZİANTEP'),
(28,'GİRESUN'),
(29,'GÜMÜŞHANE'),
(30,'HAKKARİ'),
(31,'HATAY'),
(32,'ISPARTA'),
(33,'MERSİN'),
(34,'İSTANBUL'),
(35,'İZMİR'),
(36,'KARS'),
(37,'KASTAMONU'),
(38,'KAYSERİ'),
(39,'KIRKLARELİ'),
(40,'KIRŞEHİR'),
(41,'KOCAELİ'),
(42,'KONYA'),
(43,'KÜTAHYA'),
(44,'MALATYA'),
(45,'MANİSA'),
(46,'KAHRAMANMARAŞ'),
(47,'MARDİN'),
(48,'MUĞLA'),
(49,'MUŞ'),
(50,'NEVŞEHİR'),
(51,'NİĞDE'),
(52,'ORDU'),
(53,'RİZE'),
(54,'SAKARYA'),
(55,'SAMSUN'),
(56,'SİİRT'),
(57,'SİNOP'),
(58,'SİVAS'),
(59,'TEKİRDAĞ'),
(60,'TOKAT'),
(61,'TRABZON'),
(62,'TUNCELİ'),
(63,'ŞANLIURFA'),
(64,'UŞAK'),
(65,'VAN'),
(66,'YOZGAT'),
(67,'ZONGULDAK'),
(68,'AKSARAY'),
(69,'BAYBURT'),
(70,'KARAMAN'),
(71,'KIRIKKALE'),
(72,'BATMAN'),
(73,'ŞIRNAK'),
(74,'BARTIN'),
(75,'ARDAHAN'),
(76,'IĞDIR'),
(77,'YALOVA'),
(78,'KARABÜK'),
(79,'KİLİS'),
(80,'OSMANİYE'),
(81,'DÜZCE');

INSERT INTO languages (id, language) VALUES ('ab', 'Abkhazian'),
('ace', 'Achinese'),
('ach', 'Acoli'),
('ada', 'Adangme'),
('ady', 'Adyghe'),
('aa', 'Afar'),
('afh', 'Afrihili'),
('af', 'Afrikaans'),
('agq', 'Aghem'),
('ain', 'Ainu')
,('ak', 'Akan')
,('akk', 'Akkadian')
,('bss', 'Akoose')
,('akz', 'Alabama')
,('sq', 'Albanian')
,('ale', 'Aleut')
,('arq', 'Algerian Arabic')
,('am', 'Amarik')
,('en_US', 'American English')
,('ase', 'American Sign Language')
,('egy', 'Ancient Egyptian')
,('grc', 'Ancient Greek')
,('anp', 'Angika')
,('njo', 'Ao Naga')
,('ar', 'Arabik')
,('an', 'Aragonese')
,('arc', 'Aramaic')
,('aro', 'Araona')
,('arp', 'Arapaho')
,('arw', 'Arawak')
,('hy', 'Armenian')
,('rup', 'Aromanian')
,('frp', 'Arpitan')
,('as', 'Assamese')
,('ast', 'Asturian')
,('asa', 'Asu')
,('cch', 'Atsam')
,('en_AU', 'Australian English')
,('de_AT', 'Austrian German')
,('av', 'Avaric')
,('ae', 'Avestan')
,('awa', 'Awadhi')
,('ay', 'Aymara')
,('az', 'Azerbaijani')
,('bfq', 'Badaga')
,('ksf', 'Bafia')
,('bfd', 'Bafut')
,('bqi', 'Bakhtiari')
,('ban', 'Balinese')
,('bal', 'Baluchi')
,('bm', 'Bambara')
,('bax', 'Bamun')
,('bjn', 'Banjar')
,('bas', 'Basaa')
,('ba', 'Bashkir')
,('eu', 'Basque')
,('bbc', 'Batak Toba')
,('bar', 'Bavarian')
,('bej', 'Beja')
,('be', 'Belarus kasa')
,('bem', 'Bemba')
,('bez', 'Bena')
,('bn', 'Bengali kasa')
,('bew', 'Betawi')
,('my', 'Bɛɛmis kasa')
,('bho', 'Bhojpuri')
,('bik', 'Bikol')
,('bin', 'Bini')
,('bpy', 'Bishnupriya')
,('bi', 'Bislama')
,('byn', 'Blin')
,('zbl', 'Blissymbols')
,('brx', 'Bodo')
,('en', 'Borɔfo')
,('bs', 'Bosnian')
,('bg', 'Bɔlgeria kasa')
,('brh', 'Brahui')
,('bra', 'Braj')
,('pt_BR', 'Brazilian Portuguese')
,('br', 'Breton')
,('en_GB', 'British English')
,('bug', 'Buginese')
,('bum', 'Bulu')
,('bua', 'Buriat')
,('cad', 'Caddo')
,('frc', 'Cajun French')
,('en_CA', 'Canadian English')
,('fr_CA', 'Canadian French')
,('yue', 'Cantonese')
,('cps', 'Capiznon')
,('car', 'Carib')
,('ca', 'Catalan')
,('cay', 'Cayuga')
,('ceb', 'Cebuano')
,('tzm', 'Central Atlas Tamazight')
,('dtp', 'Central Dusun')
,('ckb', 'Central Kurdish')
,('esu', 'Central Yupik')
,('shu', 'Chadian Arabic')
,('chg', 'Chagatai')
,('ch', 'Chamorro')
,('ce', 'Chechen')
,('chr', 'Cherokee')
,('chy', 'Cheyenne')
,('chb', 'Chibcha')
,('cgg', 'Chiga')
,('qug', 'Chimborazo Highland Quichua')
,('chn', 'Chinook Jargon')
,('chp', 'Chipewyan')
,('cho', 'Choctaw')
,('cu', 'Church Slavic')
,('chk', 'Chuukese')
,('cv', 'Chuvash')
,('nwc', 'Classical Newari')
,('syc', 'Classical Syriac')
,('ksh', 'Colognian')
,('swb', 'Comorian')
,('swc', 'Congo Swahili')
,('cop', 'Coptic')
,('kw', 'Cornish')
,('co', 'Corsican')
,('cr', 'Cree')
,('mus', 'Creek')
,('crh', 'Crimean Turkish')
,('hr', 'Croatian')
,('dak', 'Dakota')
,('da', 'Danish')
,('dar', 'Dargwa')
,('dzg', 'Dazaga')
,('del', 'Delaware')
,('nl', 'Dɛɛkye')
,('din', 'Dinka')
,('dv', 'Divehi')
,('doi', 'Dogri')
,('dgr', 'Dogrib')
,('dua', 'Duala')
,('dyu', 'Dyula')
,('dz', 'Dzongkha')
,('frs', 'Eastern Frisian')
,('efi', 'Efik')
,('arz', 'Egyptian Arabic')
,('eka', 'Ekajuk')
,('elx', 'Elamite')
,('ebu', 'Embu')
,('egl', 'Emilian')
,('myv', 'Erzya')
,('eo', 'Esperanto')
,('et', 'Estonian')
,('pt_PT', 'European Portuguese')
,('es_ES', 'European Spanish')
,('ee', 'Ewe')
,('ewo', 'Ewondo')
,('ext', 'Extremaduran')
,('fan', 'Fang')
,('fat', 'Fanti')
,('fo', 'Faroese')
,('hif', 'Fiji Hindi')
,('fj', 'Fijian')
,('fil', 'Filipino')
,('fi', 'Finnish')
,('nl_BE', 'Flemish')
,('fon', 'Fon')
,('gur', 'Frafra')
,('fr', 'Frɛnkye')
,('fur', 'Friulian')
,('ff', 'Fulah')
,('gaa', 'Ga')
,('gag', 'Gagauz')
,('gl', 'Galician')
,('gan', 'Gan Chinese')
,('lg', 'Ganda')
,('gay', 'Gayo')
,('gba', 'Gbaya')
,('gez', 'Geez')
,('ka', 'Georgian')
,('aln', 'Gheg Albanian')
,('bbj', 'Ghomala')
,('glk', 'Gilaki')
,('gil', 'Gilbertese')
,('gom', 'Goan Konkani')
,('gon', 'Gondi')
,('gor', 'Gorontalo')
,('got', 'Gothic')
,('grb', 'Grebo')
,('el', 'Greek kasa')
,('gn', 'Guarani')
,('gu', 'Gujarati')
,('guz', 'Gusii')
,('gwi', 'Gwichʼin')
,('de', 'Gyaaman')
,('jv', 'Gyabanis kasa')
,('ja', 'Gyapan kasa')
,('hai', 'Haida')
,('ht', 'Haitian')
,('hak', 'Hakka Chinese')
,('hu', 'Hangri kasa')
,('ha', 'Hausa')
,('haw', 'Hawaiian')
,('he', 'Hebrew')
,('hz', 'Herero')
,('hil', 'Hiligaynon')
,('hi', 'Hindi')
,('ho', 'Hiri Motu')
,('hit', 'Hittite')
,('hmn', 'Hmong')
,('hup', 'Hupa')
,('iba', 'Iban')
,('ibb', 'Ibibio')
,('is', 'Icelandic')
,('io', 'Ido')
,('ig', 'Igbo')
,('ilo', 'Iloko')
,('smn', 'Inari Sami')
,('id', 'Indonihyia kasa')
,('izh', 'Ingrian')
,('inh', 'Ingush')
,('ia', 'Interlingua')
,('ie', 'Interlingue')
,('iu', 'Inuktitut')
,('ik', 'Inupiaq')
,('ga', 'Irish')
,('it', 'Italy kasa')
,('jam', 'Jamaican Creole English')
,('kaj', 'Jju')
,('dyo', 'Jola-Fonyi')
,('jrb', 'Judeo-Arabic')
,('jpr', 'Judeo-Persian')
,('jut', 'Jutish')
,('kbd', 'Kabardian')
,('kea', 'Kabuverdianu')
,('kab', 'Kabyle')
,('kac', 'Kachin')
,('kgp', 'Kaingang')
,('kkj', 'Kako')
,('kl', 'Kalaallisut')
,('kln', 'Kalenjin')
,('xal', 'Kalmyk')
,('kam', 'Kamba')
,('km', 'Kambodia kasa')
,('kbl', 'Kanembu')
,('kn', 'Kannada')
,('kr', 'Kanuri')
,('kaa', 'Kara-Kalpak')
,('krc', 'Karachay-Balkar')
,('krl', 'Karelian')
,('ks', 'Kashmiri')
,('csb', 'Kashubian')
,('kaw', 'Kawi')
,('kk', 'Kazakh')
,('ken', 'Kenyang')
,('kha', 'Khasi')
,('kho', 'Khotanese')
,('khw', 'Khowar')
,('ki', 'Kikuyu')
,('kmb', 'Kimbundu')
,('krj', 'Kinaray-a')
,('kiu', 'Kirmanjki')
,('tlh', 'Klingon')
,('bkm', 'Kom')
,('kv', 'Komi')
,('koi', 'Komi-Permyak')
,('kg', 'Kongo')
,('kok', 'Konkani')
,('ko', 'Korea kasa')
,('kfo', 'Koro')
,('kos', 'Kosraean')
,('avk', 'Kotava')
,('khq', 'Koyra Chiini')
,('ses', 'Koyraboro Senni')
,('kpe', 'Kpelle')
,('kri', 'Krio')
,('kj', 'Kuanyama')
,('kum', 'Kumyk')
,('ku', 'Kurdish')
,('kru', 'Kurukh')
,('kut', 'Kutenai')
,('nmg', 'Kwasio')
,('zh', 'Kyaena kasa')
,('cs', 'Kyɛk kasa')
,('ky', 'Kyrgyz')
,('quc', 'Kʼicheʼ')
,('lad', 'Ladino')
,('lah', 'Lahnda')
,('lkt', 'Lakota')
,('lam', 'Lamba')
,('lag', 'Langi')
,('lo', 'Lao')
,('ltg', 'Latgalian')
,('la', 'Latin')
,('es_419', 'Latin American Spanish')
,('lv', 'Latvian')
,('lzz', 'Laz')
,('lez', 'Lezghian')
,('lij', 'Ligurian')
,('li', 'Limburgish')
,('ln', 'Lingala')
,('lfn', 'Lingua Franca Nova')
,('lzh', 'Literary Chinese')
,('lt', 'Lithuanian')
,('liv', 'Livonian')
,('jbo', 'Lojban')
,('lmo', 'Lombard')
,('nds', 'Low German')
,('sli', 'Lower Silesian')
,('dsb', 'Lower Sorbian')
,('loz', 'Lozi')
,('lu', 'Luba-Katanga')
,('lua', 'Luba-Lulua')
,('lui', 'Luiseno')
,('smj', 'Lule Sami')
,('lun', 'Lunda')
,('luo', 'Luo')
,('lb', 'Luxembourgish')
,('luy', 'Luyia')
,('mde', 'Maba')
,('mk', 'Macedonian')
,('jmc', 'Machame')
,('mad', 'Madurese')
,('maf', 'Mafa')
,('mag', 'Magahi')
,('vmf', 'Main-Franconian')
,('mai', 'Maithili')
,('mak', 'Makasar')
,('mgh', 'Makhuwa-Meetto')
,('kde', 'Makonde')
,('mg', 'Malagasy')
,('ms', 'Malay kasa')
,('ml', 'Malayalam')
,('mt', 'Maltese')
,('mnc', 'Manchu')
,('mdr', 'Mandar')
,('man', 'Mandingo')
,('mni', 'Manipuri')
,('gv', 'Manx')
,('mi', 'Maori')
,('arn', 'Mapuche')
,('mr', 'Marathi')
,('chm', 'Mari')
,('mh', 'Marshallese')
,('mwr', 'Marwari')
,('mas', 'Masai')
,('mzn', 'Mazanderani')
,('byv', 'Medumba')
,('men', 'Mende')
,('mwv', 'Mentawai')
,('mer', 'Meru')
,('mgo', 'Metaʼ')
,('es_MX', 'Mexican Spanish')
,('mic', 'Micmac')
,('dum', 'Middle Dutch')
,('enm', 'Middle English')
,('frm', 'Middle French')
,('gmh', 'Middle High German')
,('mga', 'Middle Irish')
,('nan', 'Min Nan Chinese')
,('min', 'Minangkabau')
,('xmf', 'Mingrelian')
,('mwl', 'Mirandese')
,('lus', 'Mizo')
,('ar_001', 'Modern Standard Arabic')
,('moh', 'Mohawk')
,('mdf', 'Moksha')
,('ro_MD', 'Moldavian')
,('lol', 'Mongo')
,('mn', 'Mongolian')
,('mfe', 'Morisyen')
,('ary', 'Moroccan Arabic')
,('mos', 'Mossi')
,('mul', 'Multiple Languages')
,('mua', 'Mundang')
,('ttt', 'Muslim Tat')
,('mye', 'Myene')
,('naq', 'Nama')
,('na', 'Nauru')
,('nv', 'Navajo')
,('ng', 'Ndonga')
,('nap', 'Neapolitan')
,('new', 'Newari')
,('ne', 'Nɛpal kasa')
,('sba', 'Ngambay')
,('nnh', 'Ngiemboon')
,('jgo', 'Ngomba')
,('yrl', 'Nheengatu')
,('nia', 'Nias')
,('niu', 'Niuean')
,('zxx', 'No linguistic content')
,('nog', 'Nogai')
,('nd', 'North Ndebele')
,('frr', 'Northern Frisian')
,('se', 'Northern Sami')
,('nso', 'Northern Sotho')
,('no', 'Norwegian')
,('nb', 'Norwegian Bokmål')
,('nn', 'Norwegian Nynorsk')
,('nov', 'Novial')
,('nus', 'Nuer')
,('nym', 'Nyamwezi')
,('ny', 'Nyanja')
,('nyn', 'Nyankole')
,('tog', 'Nyasa Tonga')
,('nyo', 'Nyoro')
,('nzi', 'Nzima')
,('nqo', 'NʼKo')
,('oc', 'Occitan')
,('oj', 'Ojibwa')
,('ang', 'Old English')
,('fro', 'Old French')
,('goh', 'Old High German')
,('sga', 'Old Irish')
,('non', 'Old Norse')
,('peo', 'Old Persian')
,('pro', 'Old Provençal')
,('or', 'Oriya')
,('om', 'Oromo')
,('osa', 'Osage')
,('os', 'Ossetic')
,('ota', 'Ottoman Turkish')
,('pal', 'Pahlavi')
,('pfl', 'Palatine German')
,('pau', 'Palauan')
,('pi', 'Pali')
,('pam', 'Pampanga')
,('pag', 'Pangasinan')
,('pap', 'Papiamento')
,('ps', 'Pashto')
,('pdc', 'Pennsylvania German')
,('fa', 'Pɛɛhyia kasa')
,('phn', 'Phoenician')
,('pcd', 'Picard')
,('pms', 'Piedmontese')
,('pdt', 'Plautdietsch')
,('pon', 'Pohnpeian')
,('pnt', 'Pontic')
,('pl', 'Pɔland kasa')
,('pt', 'Pɔɔtugal kasa')
,('prg', 'Prussian')
,('pa', 'Pungyabi kasa')
,('qu', 'Quechua')
,('ru', 'Rahyia kasa')
,('raj', 'Rajasthani')
,('rap', 'Rapanui')
,('rar', 'Rarotongan')
,('rw', 'Rewanda kasa')
,('rif', 'Riffian')
,('rgn', 'Romagnol')
,('rm', 'Romansh')
,('rom', 'Romany')
,('rof', 'Rombo')
,('ro', 'Romenia kasa')
,('root', 'Root')
,('rtm', 'Rotuman')
,('rug', 'Roviana')
,('rn', 'Rundi')
,('rue', 'Rusyn')
,('rwk', 'Rwa')
,('ssy', 'Saho')
,('sah', 'Sakha')
,('sam', 'Samaritan Aramaic')
,('saq', 'Samburu')
,('sm', 'Samoan')
,('sgs', 'Samogitian')
,('sad', 'Sandawe')
,('sg', 'Sango')
,('sbp', 'Sangu')
,('sa', 'Sanskrit')
,('sat', 'Santali')
,('sc', 'Sardinian')
,('sas', 'Sasak')
,('sdc', 'Sassarese Sardinian')
,('stq', 'Saterland Frisian')
,('saz', 'Saurashtra')
,('sco', 'Scots')
,('gd', 'Scottish Gaelic')
,('sly', 'Selayar')
,('sel', 'Selkup')
,('seh', 'Sena')
,('see', 'Seneca')
,('sr', 'Serbian')
,('sh', 'Serbo-Croatian')
,('srr', 'Serer')
,('sei', 'Seri')
,('ksb', 'Shambala')
,('shn', 'Shan')
,('sn', 'Shona')
,('ii', 'Sichuan Yi')
,('scn', 'Sicilian')
,('sid', 'Sidamo')
,('bla', 'Siksika')
,('szl', 'Silesian')
,('zh_Hans', 'Simplified Chinese')
,('sd', 'Sindhi')
,('si', 'Sinhala')
,('sms', 'Skolt Sami')
,('den', 'Slave')
,('sk', 'Slovak')
,('sl', 'Slovenian')
,('xog', 'Soga')
,('sog', 'Sogdien')
,('so', 'Somalia kasa')
,('snk', 'Soninke')
,('azb', 'South Azerbaijani')
,('nr', 'South Ndebele')
,('alt', 'Southern Altai')
,('sma', 'Southern Sami')
,('st', 'Southern Sotho')
,('es', 'Spain kasa')
,('srn', 'Sranan Tongo')
,('zgh', 'Standard Moroccan Tamazight')
,('suk', 'Sukuma')
,('sux', 'Sumerian')
,('su', 'Sundanese')
,('sus', 'Susu')
,('sw', 'Swahili')
,('ss', 'Swati')
,('sv', 'Sweden kasa')
,('fr_CH', 'Swiss French')
,('gsw', 'Swiss German')
,('de_CH', 'Swiss High German')
,('syr', 'Syriac')
,('shi', 'Tachelhit')
,('th', 'Taeland kasa')
,('tl', 'Tagalog')
,('ty', 'Tahitian')
,('dav', 'Taita')
,('tg', 'Tajik')
,('tly', 'Talysh')
,('tmh', 'Tamashek')
,('ta', 'Tamil kasa')
,('trv', 'Taroko')
,('twq', 'Tasawaq')
,('tt', 'Tatar')
,('te', 'Telugu')
,('ter', 'Tereno')
,('teo', 'Teso')
,('tet', 'Tetum')
,('tr', 'Tɛɛki kasa')
,('bo', 'Tibetan')
,('tig', 'Tigre')
,('ti', 'Tigrinya')
,('tem', 'Timne')
,('tiv', 'Tiv')
,('tli', 'Tlingit')
,('tpi', 'Tok Pisin')
,('tkl', 'Tokelau')
,('to', 'Tongan')
,('fit', 'Tornedalen Finnish')
,('zh_Hant', 'Traditional Chinese')
,('tkr', 'Tsakhur')
,('tsd', 'Tsakonian')
,('tsi', 'Tsimshian')
,('ts', 'Tsonga')
,('tn', 'Tswana')
,('tcy', 'Tulu')
,('tum', 'Tumbuka')
,('aeb', 'Tunisian Arabic')
,('tk', 'Turkmen')
,('tru', 'Turoyo')
,('tvl', 'Tuvalu')
,('tyv', 'Tuvinian')
,('tw', 'Twi')
,('kcg', 'Tyap')
,('udm', 'Udmurt')
,('uga', 'Ugaritic')
,('uk', 'Ukren kasa')
,('umb', 'Umbundu')
,('und', 'Unknown Language')
,('hsb', 'Upper Sorbian')
,('ur', 'Urdu kasa')
,('ug', 'Uyghur')
,('uz', 'Uzbek')
,('vai', 'Vai')
,('ve', 'Venda')
,('vec', 'Venetian')
,('vep', 'Veps')
,('vi', 'Viɛtnam kasa')
,('vo', 'Volapük')
,('vro', 'Võro')
,('vot', 'Votic')
,('vun', 'Vunjo')
,('wa', 'Walloon')
,('wae', 'Walser')
,('war', 'Waray')
,('wbp', 'Warlpiri')
,('was', 'Washo')
,('guc', 'Wayuu')
,('cy', 'Welsh')
,('vls', 'West Flemish')
,('fy', 'Western Frisian')
,('mrj', 'Western Mari')
,('wal', 'Wolaytta')
,('wo', 'Wolof')
,('wuu', 'Wu Chinese')
,('xh', 'Xhosa')
,('hsn', 'Xiang Chinese')
,('yav', 'Yangben')
,('yao', 'Yao')
,('yap', 'Yapese')
,('ybb', 'Yemba')
,('yi', 'Yiddish')
,('yo', 'Yoruba')
,('zap', 'Zapotec')
,('dje', 'Zarma')
,('zza', 'Zaza')
,('zea', 'Zeelandic')
,('zen', 'Zenaga')
,('za', 'Zhuang')
,('gbz', 'Zoroastrian Dari')
,('zu', 'Zulu')
,('zun', 'Zuni');