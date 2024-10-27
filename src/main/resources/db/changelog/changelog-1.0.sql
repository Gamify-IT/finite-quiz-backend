-- liquibase formatted sql

-- changeset leon:change1-1
CREATE TABLE "game_result" ("id" UUID NOT NULL, "configuration_asuuid" UUID NOT NULL, "played_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "player_id" VARCHAR(255) NOT NULL, "question_count" INTEGER NOT NULL, "score" BIGINT NOT NULL, "time_spent" BIGINT NOT NULL, CONSTRAINT "game_result_pkey" PRIMARY KEY ("id"));

-- changeset leon:change1-2
CREATE TABLE "configuration_questions" ("configuration_id" UUID NOT NULL, "questions_id" UUID NOT NULL, CONSTRAINT "configuration_questions_pkey" PRIMARY KEY ("configuration_id", "questions_id"));

-- changeset leon:change1-3
ALTER TABLE "configuration_questions" ADD CONSTRAINT "uk_87jmj05cn4rqb8wfq6qxej42w" UNIQUE ("questions_id");

-- changeset leon:change1-4
CREATE TABLE "configuration" ("id" UUID NOT NULL, CONSTRAINT "configuration_pkey" PRIMARY KEY ("id"));

-- changeset leon:change1-5
CREATE TABLE "game_result_correct_answered_questions" ("game_result_id" UUID NOT NULL, "correct_answered_questions_id" UUID NOT NULL);

-- changeset leon:change1-6
CREATE TABLE "game_result_wrong_answered_questions" ("game_result_id" UUID NOT NULL, "wrong_answered_questions_id" UUID NOT NULL);

-- changeset leon:change1-7
CREATE TABLE "question" ("id" UUID NOT NULL, "right_answer" VARCHAR(255) NOT NULL, "text" VARCHAR(255) NOT NULL, CONSTRAINT "question_pkey" PRIMARY KEY ("id"));

-- changeset leon:change1-8
CREATE TABLE "question_wrong_answers" ("question_id" UUID NOT NULL, "wrong_answers" VARCHAR(255));

-- changeset leon:change1-9
CREATE TABLE "round_result" ("id" UUID NOT NULL, "answer" VARCHAR(255) NOT NULL, "question_id" UUID NOT NULL, CONSTRAINT "round_result_pkey" PRIMARY KEY ("id"));

-- changeset leon:change1-10
ALTER TABLE "game_result_correct_answered_questions" ADD CONSTRAINT "fk2yr4n6edjx6h62qhjfj0x0n9h" FOREIGN KEY ("correct_answered_questions_id") REFERENCES "round_result" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-11
ALTER TABLE "game_result_wrong_answered_questions" ADD CONSTRAINT "fk5l5weg5gvyjdyjutrqiigreqc" FOREIGN KEY ("wrong_answered_questions_id") REFERENCES "round_result" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-12
ALTER TABLE "question_wrong_answers" ADD CONSTRAINT "fk9thusvh2s8wjgxjf3gkwr7bnu" FOREIGN KEY ("question_id") REFERENCES "question" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-13
ALTER TABLE "game_result_wrong_answered_questions" ADD CONSTRAINT "fkbmqsqjwwrhconh1qhvfv7nyta" FOREIGN KEY ("game_result_id") REFERENCES "game_result" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-14
ALTER TABLE "configuration_questions" ADD CONSTRAINT "fkewy22y8x7me09uka66yaovavm" FOREIGN KEY ("questions_id") REFERENCES "question" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-15
ALTER TABLE "round_result" ADD CONSTRAINT "fknbh8yrgf47myl1mfiv2johows" FOREIGN KEY ("question_id") REFERENCES "question" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-16
ALTER TABLE "configuration_questions" ADD CONSTRAINT "fkpuxg1dtbsi0no6cj8ynv0f8tt" FOREIGN KEY ("configuration_id") REFERENCES "configuration" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset leon:change1-17
ALTER TABLE "game_result_correct_answered_questions" ADD CONSTRAINT "fkrf30lgepnva24yiwi6en9oedc" FOREIGN KEY ("game_result_id") REFERENCES "game_result" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

