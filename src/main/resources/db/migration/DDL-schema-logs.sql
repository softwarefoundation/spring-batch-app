CREATE TABLE logs.logging_event (
	event_id serial NOT NULL,
	created_at timestamp NULL DEFAULT now(),
	timestmp int8 NOT NULL,
	formatted_message text NOT NULL,
	logger_name varchar(254) NOT NULL,
	level_string varchar(254) NOT NULL,
	thread_name varchar(254) NULL,
	reference_flag int2 NULL,
	arg0 varchar(254) NULL,
	arg1 varchar(254) NULL,
	arg2 varchar(254) NULL,
	arg3 varchar(254) NULL,
	caller_filename varchar(254) NOT NULL,
	caller_class varchar(254) NOT NULL,
	caller_method varchar(254) NOT NULL,
	caller_line bpchar(4) NOT NULL,
	CONSTRAINT logging_event_pkey PRIMARY KEY (event_id)
);


-- logs.logging_event_exception definition

-- Drop table

-- DROP TABLE logs.logging_event_exception;

CREATE TABLE logs.logging_event_exception (
	event_id int8 NOT NULL,
	created_at timestamp NULL DEFAULT now(),
	i int2 NOT NULL,
	trace_line varchar(254) NOT NULL,
	CONSTRAINT logging_event_exception_pkey PRIMARY KEY (event_id, i),
	CONSTRAINT logging_event_exception_event_id_fkey FOREIGN KEY (event_id) REFERENCES logs.logging_event(event_id)
);


-- logs.logging_event_property definition

-- Drop table

-- DROP TABLE logs.logging_event_property;

CREATE TABLE logs.logging_event_property (
	event_id int8 NOT NULL,
	created_at timestamp NULL DEFAULT now(),
	mapped_key varchar(254) NOT NULL,
	mapped_value text NULL,
	CONSTRAINT logging_event_property_pkey PRIMARY KEY (event_id, mapped_key),
	CONSTRAINT logging_event_property_event_id_fkey FOREIGN KEY (event_id) REFERENCES logs.logging_event(event_id)
);
