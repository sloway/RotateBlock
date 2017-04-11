# RotateBlock

## Rotation logic for N by N block 

	This function is the core logic for rotation
	This logic arranges the travel path based on the rotation degree
	For example rotation 90 degree,
	1 2 3 
	4 5 6
	7 8 9
	
	(Source) Reading order : 7 4 1 8 5 2 9 6 3
	(Target) Writing order : 1 2 3 4 5 6 7 8 9 
	
	
	extra info
	Rotate 90  : Row=>Col, Col(I)=>Row
	Rotate 180 : Row(I)=>Row, Col(I)=>Col. 
	Rotate 270 : Row(I)=>Col, Col=>Row
		 