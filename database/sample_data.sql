INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

#create user through app

#EXAMPLE CASE 1
INSERT INTO detective_case(creator, name, description, image, ready, max_days, mp_per_day) 
VALUES (1, "Zaginiony dlugopis", "Pomoz uczniowi odkryc kto ukradl jego ulubiony dlugopis", null, 1, 2,6);

INSERT INTO LOCATION(case_id, name, description, image, is_Start) VALUES (1, "szkola", "Szkola Podstawowa nr 46 we Wroclawiu do ktorej uczeszczasz", null, true);
INSERT INTO LOCATION(case_id, name, description, image, is_Start) VALUES (1, "dom", "Blok mieszkalny przy ul. Slicznej, troche podstarzaly. Twoja rodzina mieszka tu od lat. Raczej nie zdarzaja sie wlamania. Poza tym, kto by ukradl dlugopis? No chyba że mama.", null, false);

INSERT INTO LOCATION_CONNECTION(from_id, to_id, time) VALUES (1,2,4);

INSERT INTO ITEM(name, description, image, exam_result, type_of_item) VALUES("dlugopis kumpla", "Prosty dlugopis z czarnym atramentem", null, "dlugopis jest identyczny do twojego. Nawet tak samo nadgryziony. Podejrzane.", "I");
INSERT INTO ITEM(name, description, image, exam_result, type_of_item) VALUES("dlugopisy nauczyciela", "Sterta dlugopisow lezaca na biurku nauczyciela. Jest ich na tyle duzo ze ciezko powiedziec czy znajduje się tam twój.", null, "Raczej zaden z nich nie wyglada na twoj.", "I");

INSERT INTO ITEM(name, description, image, exam_result, type_of_item) VALUES("Halina Abacka", "Nauczycielka w szkole podstawowej nr 46. Mężatka. Kasztanowe włosy i niebieskie oczy. Uczniowie raczej nie darzą jej sympatią. Bardzo wymagająca.", null, null, "P");
INSERT INTO ITEM(name, description, image, exam_result, type_of_item) VALUES("Maciej Kędzia", "Uczeń w szkole podstawowej nr 46. 10 lat. Nadpobudliwy.", null, null, "P");

INSERT INTO ACTION(name, description, image, time, case_id, location) VALUES ("Początek", "Siedzisz na lekcji matematyki. To już druga z rzędu lekcja w tej samej sali. Nie uważałeś za bardzo, ale na początku poprzedniej lekcji zapisałeś w zeszycie chociaż temat. Po godzinie nieuważania nagle orientujesz się, że zadanie jest dosyć trudne, więc wypadałoby sobie je zapisać w zeszycie. Zabierasz się już do zapisywania, ale zaraz... Gdzie jest twój ulubiony (i zresztą jedyny) długopis!?", null, 0, 1, 1);

UPDATE DETECTIVE_CASE SET frst_action_id = 1 WHERE ID = 1;
INSERT INTO ACTION_ITEM(action_id,item_id) VALUES (1,3);
INSERT INTO ACTION_ITEM(action_id,item_id) VALUES (1,4);

INSERT INTO ACTION(name, description, image, time, case_id, location) VALUES ("Obczaj biurko nauczycielki", "Nauczycielka często pożycza od kogoś długopisy i nigdy nie oddaje. Pod byle pretekstem podchodzisz do jej biurka i sprawdzasz czy nie zabrała twojego długopisu. Na biurku jest ich niestety mnóstwo i ciężko ci dostrzec czy któryś nie jest twoim ulubionym.",
null, 2, 1, 1);

INSERT INTO ACTION_ITEM(action_id,item_id) VALUES (2,2);

INSERT INTO ACTION(name, description, image, time, case_id, location) VALUES ("Zarzuć koledze z ławki", "To na pewno Maciek! On zawsze patrzył z zazdrością na twój długopis. Pultasz się do niego strasznie. Maciek nie wie co ci odbiło. Odpowiada, że na pewno zostawiłeś długopis w domu, a temat na początku lekcji napisałeś którymś z jego długopisów. Może ma racje, a ty  rzeczywiście się rzucasz bez powodu? Coś jednak wydaje Ci się podejrzane w uśmieszku Maćka.",null, 2, 1, 1);

INSERT INTO ACTION_LOCATION(action_id, location_id) VALUES (3,2);

INSERT INTO ACTION(name, description, image, time, case_id, location) VALUES ("Sprawdz w domu", "Zrezygnowany poszukiwaniami w szkole, przegladasz biurko w domu. Nie ma. Patrzysz w  szufladzie, też nie ma. Chyba już nigdy nie odzyskasz długopisu, chyba że złodziej się przyzna...",null, 2, 1, 2);

INSERT INTO ACTION_ACTION(action_id,revealed_id) VALUES(3,4);

INSERT INTO ACTION(name, description, image, time, case_id, location) VALUES ("Zapytaj miło kolege z ławki", "Pytasz Maćka czy nie zabrał przypadkiem twojego długopisu. Maciek lekko zdenerwowany
odpowiada, że nie. Proponuj jednak, że pożyczy ci swój.",null, 2, 1, 1);

INSERT INTO ACTION_ITEM(action_id,item_id) VALUES (5,1);

INSERT INTO ACTION_ACTION(action_id,revealed_id) VALUES(1,2);
INSERT INTO ACTION_ACTION(action_id,revealed_id) VALUES(1,3);
INSERT INTO ACTION_ACTION(action_id,revealed_id) VALUES(1,5);

INSERT INTO QUESTION(case_id,content) VALUES (1,"Kto ukradl dlugopis?");
INSERT INTO ANSWER(question_id,content,correct) VALUES (1,"Kolega",True);
INSERT INTO ANSWER(question_id,content,correct) VALUES (1,"Nauczyciel",False);
INSERT INTO ANSWER(question_id,content,correct) VALUES (1,"Nikt. sam go zgubiłeś.",False);

