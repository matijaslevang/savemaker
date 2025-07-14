INSERT INTO categories (name, is_used_for_income) VALUES
                                                      ('Salary', true),
                                                      ('Freelance', true),
                                                      ('Groceries', false),
                                                      ('Utilities', false),
                                                      ('Entertainment', false);

INSERT INTO transactions (amount, date, notes, category_id) VALUES
                                                                (1500.00, '2025-07-01', 'Monthly salary', 1),
                                                                (800.50, '2025-07-05', 'Freelance project', 2),
                                                                (75.30, '2025-07-06', 'Supermarket shopping', 3),
                                                                (120.00, '2025-07-07', 'Electricity bill', 4),
                                                                (50.00, '2025-07-08', 'Movie night', 5),
                                                                (1500.00, '2025-06-01', 'Monthly salary', 1),
                                                                (400.00, '2025-06-10', 'Freelance design work', 2),
                                                                (100.00, '2025-06-12', 'Groceries', 3),
                                                                (90.00, '2025-06-15', 'Water bill', 4),
                                                                (30.00, '2025-06-18', 'Concert ticket', 5),
                                                                (1600.00, '2025-05-01', 'Monthly salary', 1),
                                                                (750.00, '2025-05-03', 'Freelance writing', 2),
                                                                (80.00, '2025-05-05', 'Grocery store', 3),
                                                                (110.00, '2025-05-07', 'Gas bill', 4),
                                                                (45.00, '2025-05-10', 'Video games', 5),
                                                                (1550.00, '2025-04-01', 'Monthly salary', 1),
                                                                (900.00, '2025-04-06', 'Freelance project', 2),
                                                                (60.00, '2025-04-08', 'Groceries', 3),
                                                                (95.00, '2025-04-10', 'Internet bill', 4),
                                                                (40.00, '2025-04-12', 'Dinner out', 5);
