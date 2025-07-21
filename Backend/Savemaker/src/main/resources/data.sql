INSERT INTO categories (id, name, is_used_for_income, preferred_spending_category_id) VALUES
                                                                                          (DEFAULT, 'Salary', true, NULL),
                                                                                          (DEFAULT, 'Freelance', true, NULL),
                                                                                          (DEFAULT, 'Food', false, 1),
                                                                                          (DEFAULT, 'Transport', false, 1),
                                                                                          (DEFAULT, 'Entertainment', false, 2);
INSERT INTO income_type_balances (id, category_id, balance, priority) VALUES
                                                                (DEFAULT, 1, 3000.00, 1),
                                                                (DEFAULT, 2, 1500.00, 2);

INSERT INTO main_balances (id, total_balance) VALUES
    (1, 4500.00);

INSERT INTO income_type_balance_fk_table (main_balance_id, income_type_balance_id) VALUES
                                                                                       (1, 1),
                                                                                       (1, 2);

INSERT INTO spending_details (id, category_id, amount) VALUES
                                                           (DEFAULT, 1, 200.00),
                                                           (DEFAULT, 2, 150.00),
                                                           (DEFAULT, 1, 75.50),
                                                           (DEFAULT, 2, 300.00),
                                                           (DEFAULT, 1, 100.00),
                                                           (DEFAULT, 2, 250.00),
                                                           (DEFAULT, 1, 180.00),
                                                           (DEFAULT, 2, 120.00),
                                                           (DEFAULT, 1, 90.00),
                                                           (DEFAULT, 2, 80.00),
                                                           (DEFAULT, 1, 110.00),
                                                           (DEFAULT, 2, 60.00),
                                                           (DEFAULT, 1, 95.00),
                                                           (DEFAULT, 2, 130.00),
                                                           (DEFAULT, 1, 85.00),
                                                           (DEFAULT, 1, 160.00),
                                                           (DEFAULT, 2, 140.00),
                                                           (DEFAULT, 1, 210.00),
                                                           (DEFAULT, 2, 190.00),
                                                           (DEFAULT, 1, 155.00),
                                                           (DEFAULT, 2, 170.00),
                                                           (DEFAULT, 1, 135.00),
                                                           (DEFAULT, 2, 145.00),
                                                           (DEFAULT, 1, 165.00),
                                                           (DEFAULT, 2, 185.00);

INSERT INTO transactions (id, date, notes, category_id) VALUES
                                                            (DEFAULT, '2025-07-01', 'Groceries', 3),
                                                            (DEFAULT, '2025-07-02', 'Train', 4),
                                                            (DEFAULT, '2025-07-03', 'Cinema', 5),
                                                            (DEFAULT, '2025-07-04', 'Lunch', 3),
                                                            (DEFAULT, '2025-07-05', 'Bus fare', 4),
                                                            (DEFAULT, '2025-07-06', 'Games', 5),
                                                            (DEFAULT, '2025-07-07', 'Snacks', 3),
                                                            (DEFAULT, '2025-07-08', 'Fuel', 4),
                                                            (DEFAULT, '2025-07-09', 'Music event', 5),
                                                            (DEFAULT, '2025-07-10', 'Breakfast', 3),
                                                            (DEFAULT, '2025-07-11', 'Taxi', 4),
                                                            (DEFAULT, '2025-07-12', 'Online subscription', 5),
                                                            (DEFAULT, '2025-07-13', 'Coffee', 3),
                                                            (DEFAULT, '2025-07-14', 'Uber', 4),
                                                            (DEFAULT, '2025-07-15', 'Dinner', 3);

INSERT INTO spending_details_transaction_table (transaction_id, spending_details_id) VALUES
                                                                                         (1, 1),
                                                                                         (2, 2),
                                                                                         (3, 3),
                                                                                         (4, 4),
                                                                                         (5, 5),
                                                                                         (6, 6),
                                                                                         (7, 7),
                                                                                         (8, 8),
                                                                                         (8, 17),
                                                                                         (9, 9),
                                                                                         (9, 18),
                                                                                         (10, 10),
                                                                                         (10, 19),
                                                                                         (11, 11),
                                                                                         (11, 20),
                                                                                         (12, 12),
                                                                                         (12, 21),
                                                                                         (13, 13),
                                                                                         (13, 22),
                                                                                         (14, 14),
                                                                                         (14, 23),
                                                                                         (15, 15),
                                                                                         (15, 24);




