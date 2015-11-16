CREATE table MY_SEQUENCES (
  sequence_name VARCHAR (255) NOT NULL ,
  next_val bigint ,
  PRIMARY KEY ( sequence_name )
);