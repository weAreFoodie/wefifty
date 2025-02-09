show databases;

CREATE database wefifty;

GRANT ALL PRIVILEGES ON wefifty.* to 'username'@'localhost';
GRANT ALL PRIVILEGES ON wefifty.* to 'username'@'%';

-- REVOKE ALL PRIVILEGES ON stock.* from 'username'@'localhost';
-- REVOKE ALL PRIVILEGES ON stock.* from 'username'@'%';

use wefifty;