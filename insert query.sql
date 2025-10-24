-- Dummy data for 'uom' table
INSERT INTO uom (uomid, uom_name, uom_symbol) VALUES
('u203m-5678-9012-uomid-234567890123', 'Cubic Meter', 'm³'),
('k1l0g-4321-8765-uomid-987654321098', 'Kilogram', 'kg'),
('p13c3-1122-3344-uomid-112233445566', 'Piece', 'pc'),
('l1t3r-5566-7788-uomid-556677889900', 'Liter', 'L');

-- Dummy data for 'vendor' table
INSERT INTO vendor (material_vendorid, material_vendor_contact_number, material_vendor_contact_person, material_vendor_email, material_vendor_name) VALUES
('v3e4r5-6789-0123-vendid-345678901234', '9223344556', 'Deepak Sharma', 'deepak@gmail.com', 'Construction Material Hub'),
('v3ndr-2b3c-4d5e-vendid-234567890123', '9876543211', 'Anjali Mehta', 'anjali.m@supplyco.com', 'Global Building Supplies'),
('v3ndr-3c4d-5e6f-vendid-345678901234', '8877665544', 'Vikram Singh', 'vikram.s@tradewinds.com', 'Trade Winds Hardware'),
('v3ndr-4d5e-6f7g-vendid-456789012345', '7766554433', 'Priya Rao', 'priya.r@infra.net', 'InfraKart Solutions');

-- Dummy data for 'material_type' table
INSERT INTO material_type (material_typeid, material_type_desc, material_type_name) VALUES
('t2y3p4-5678-9012-typid-234567890123', 'Steel products for construction', 'Steel'),
('typ3-cem-0001-typid-123456789012', 'Portland Pozzolana Cement', 'Cement'),
('typ3-wd-0002-typid-234567890123', 'Lumber and Plywood', 'Wood'),
('typ3-pvc-0003-typid-345678901234', 'PVC pipes and fittings', 'Plumbing');

-- Dummy data for 'manufacturer' table
INSERT INTO manufacturer (material_manufacturerid, material_manufacturer_contact_number, material_manufacturer_contact_person, material_manufacturer_email, material_manufacturer_name) VALUES
('m2a3r4-5678-9012-manid-234567890123', '9988776655', 'Suresh Patel', 'suresg@gmail.com', 'Tata Steel'),
('manuf-ult-9876-manid-987654321098', '9123456780', 'Rohan Gupta', 'rohan.g@ultratech.com', 'UltraTech Cement'),
('manuf-fin-5432-manid-543210987654', '8123456789', 'Neha Desai', 'neha.d@finolex.com', 'Finolex Pipes'),
('manuf-grn-1098-manid-109876543210', '7123456789', 'Amit Kumar', 'amit.k@greenply.com', 'Greenply Industries');