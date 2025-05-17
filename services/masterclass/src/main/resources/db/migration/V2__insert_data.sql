-- Insert sample data into the category table (5 categories)
insert into category (id, name, description) values
    (nextval('category_seq'), 'Pencil Drawing', 'Masterclasses focused on pencil-based drawing techniques'),
    (nextval('category_seq'), 'Watercolor Painting', 'Workshops for watercolor painting techniques'),
    (nextval('category_seq'), 'Charcoal Art', 'Classes for charcoal drawing and shading'),
    (nextval('category_seq'), 'Acrylic Painting', 'Hands-on sessions for acrylic painting'),
    (nextval('category_seq'), 'Sketching Techniques', 'Workshops for sketching and quick drawing');

-- Insert sample data into the masterclass table (3 masterclasses per category)
-- Pencil Drawing Masterclasses
insert into masterclass (id, name, description, date, time, seats_quantity, price, category_id) values
    (nextval('masterclass_seq'), 'Beginner Pencil Drawing', 'Introduction to pencil drawing basics', '2025-06-01 10:00:00', '10:00:00', 15, 45.00, (select id from category where name = 'Pencil Drawing')),
    (nextval('masterclass_seq'), 'Intermediate Pencil Shading', 'Learn advanced shading with pencils', '2025-06-02 13:00:00', '13:00:00', 12, 50.00, (select id from category where name = 'Pencil Drawing')),
    (nextval('masterclass_seq'), 'Portrait Drawing', 'Master pencil portrait techniques', '2025-06-03 15:00:00', '15:00:00', 10, 55.00, (select id from category where name = 'Pencil Drawing'));

-- Watercolor Painting Masterclasses
insert into masterclass (id, name, description, date, time, seats_quantity, price, category_id) values
    (nextval('masterclass_seq'), 'Watercolor Basics', 'Foundational watercolor painting techniques', '2025-06-04 10:00:00', '10:00:00', 14, 60.00, (select id from category where name = 'Watercolor Painting')),
    (nextval('masterclass_seq'), 'Landscape Watercolor', 'Create stunning watercolor landscapes', '2025-06-05 13:00:00', '13:00:00', 12, 65.00, (select id from category where name = 'Watercolor Painting')),
    (nextval('masterclass_seq'), 'Floral Watercolor', 'Focus on floral watercolor designs', '2025-06-06 15:00:00', '15:00:00', 10, 70.00, (select id from category where name = 'Watercolor Painting'));

-- Charcoal Art Masterclasses
insert into masterclass (id, name, description, date, time, seats_quantity, price, category_id) values
    (nextval('masterclass_seq'), 'Charcoal Basics', 'Introduction to charcoal drawing', '2025-06-07 10:00:00', '10:00:00', 15, 50.00, (select id from category where name = 'Charcoal Art')),
    (nextval('masterclass_seq'), 'Charcoal Portrait', 'Advanced charcoal portrait techniques', '2025-06-08 13:00:00', '13:00:00', 12, 55.00, (select id from category where name = 'Charcoal Art')),
    (nextval('masterclass_seq'), 'Charcoal Still Life', 'Master still life with charcoal', '2025-06-09 15:00:00', '15:00:00', 10, 60.00, (select id from category where name = 'Charcoal Art'));

-- Acrylic Painting Masterclasses
insert into masterclass (id, name, description, date, time, seats_quantity, price, category_id) values
    (nextval('masterclass_seq'), 'Acrylic Introduction', 'Beginner guide to acrylic painting', '2025-06-10 10:00:00', '10:00:00', 14, 65.00, (select id from category where name = 'Acrylic Painting')),
    (nextval('masterclass_seq'), 'Acrylic Landscape', 'Paint landscapes with acrylics', '2025-06-11 13:00:00', '13:00:00', 12, 70.00, (select id from category where name = 'Acrylic Painting')),
    (nextval('masterclass_seq'), 'Abstract Acrylic', 'Explore abstract acrylic techniques', '2025-06-12 15:00:00', '15:00:00', 10, 75.00, (select id from category where name = 'Acrylic Painting'));

-- Sketching Techniques Masterclasses
insert into masterclass (id, name, description, date, time, seats_quantity, price, category_id) values
    (nextval('masterclass_seq'), 'Sketching Basics', 'Learn foundational sketching skills', '2025-06-13 10:00:00', '10:00:00', 15, 45.00, (select id from category where name = 'Sketching Techniques')),
    (nextval('masterclass_seq'), 'Urban Sketching', 'Practice sketching urban scenes', '2025-06-14 13:00:00', '13:00:00', 12, 50.00, (select id from category where name = 'Sketching Techniques')),
    (nextval('masterclass_seq'), 'Figure Sketching', 'Master figure drawing techniques', '2025-06-15 15:00:00', '15:00:00', 10, 55.00, (select id from category where name = 'Sketching Techniques'));