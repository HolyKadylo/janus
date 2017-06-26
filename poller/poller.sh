prevInstr="no way this would be repeated as the instruction"
add="yourSecret.app.com/yourSecretContext"
logdir=~/Janus
pollingIter=0
exeIter=0
period=1
while true
do
	pollingIter=$((pollingIter+1))
	echo "Polling iteration " $pollingIter " period = " $period " s"
	curl $add > instructions.sh
	#TODO get rid of instructions.sh
	instr=`cat instructions.sh`
	if [ "$instr" != "$prevInstr" ]; then
		period=1	
		exeIter=$((exeIter+1))		
		echo "Executing iteration " $exeIter
		prevInstr=$instr
		date >> LOG.log
		echo $prevInstr >> LOG.log 
		#chmod +x instructions.sh
		screen -S janus -X stuff "$instr > $logdir/answer ^M"
		#./instructions.sh > answer
		#TODO update listener instead of timer
		sleep 5
		sed ':a;N;$!ba;s/\n/%0D%0A/g' answer > answer2
		value=`cat answer2`		
		echo $value >> LOG.log
		curl --data-binary "payload=$value" $add >> LOG.log
		echo " " >> LOG.log
		rm answer; rm instructions.sh; rm answer2
	fi
	echo "."; echo "."; echo "."
	sleep $period
	if [ $period != 30 ]; then
		period=$((period+1))
	fi
done
