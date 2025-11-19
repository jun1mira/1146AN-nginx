-- Initialize multiple databases for microservices
DO $$
BEGIN
   PERFORM 1 FROM pg_database WHERE datname = 'auth_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE auth_db'; END IF;

   PERFORM 1 FROM pg_database WHERE datname = 'user_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE user_db'; END IF;

   PERFORM 1 FROM pg_database WHERE datname = 'events_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE events_db'; END IF;

   PERFORM 1 FROM pg_database WHERE datname = 'guest_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE guest_db'; END IF;

   PERFORM 1 FROM pg_database WHERE datname = 'item_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE item_db'; END IF;

   PERFORM 1 FROM pg_database WHERE datname = 'notification_db';
   IF NOT FOUND THEN EXECUTE 'CREATE DATABASE notification_db'; END IF;
END
$$;