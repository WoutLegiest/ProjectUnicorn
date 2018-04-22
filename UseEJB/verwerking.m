pkg load signal 
data = [-0.029342651, -0.019966125, -0.029441833, -0.005543709, -0.03030014, -0.008390427, -0.007300377, 0.007285118, 0.014503479, -0.0072078705, -0.0060138702, -0.0011177063, -0.0058717728, 0.0012636185, 0.0055265427, -0.033883095, -0.021375656, 0.016695976, 0.011263847, -0.02177906, -0.0072546005, -0.004667282, 0.0047035217, -0.0045986176, 0.005012512, 0.019122124, 0.009218216, -0.014334679, 0.00475502, -2.632141E-4, 0.020888329, -0.014726639, -0.019425392, -0.007666588, 0.011627197, -0.007224083, -0.0048952103, -0.014305115, 0.0071697235, -0.004940033, -0.0074567795, -0.019269943, -4.863739E-5, 0.025727272, 0.003353119, 0.01266098, 0.0075626373, -0.15810776, -0.012444496, 0.264678, 0.8861151, 0.043889046, 0.11771774, 0.16410542]; 
endData = length(data) -1 ; 
 endData = endData*0.01; 
 t = 0:0.01:endData; 
 %% Step 1: resample at fixed time step 
Fs = 100.; % desired (fixed) sample rate 
%[data_resampled,t_resampled] = resample(data,t,Fs); 
t_resampled = t(1):1/Fs:t(end); 
data_resampled = interp1(t, data, t_resampled, 'spline'); 
% to make sure time starts at t = 0s: 
t_resampled = t_resampled - t_resampled(1); 
%% Step 2: Compute amplitude spectrum of the signal 
L = length(data_resampled); 
f = Fs*(0:(L/2))/L; 
A2_data = fft(data_resampled); A2 = abs(A2_data/L); 
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); 
maxi = max(A_data); 
k = find(A_data==maxi); 
disp(f(k)); 
disp('fase'); 
disp(A_data); 
disp('fase'); 
disp(f); 
disp('fase'); 
%% Step 3: Apply bandbass filter 
% Lowerbound and upperbound cutoff bandpass filter (relative to Nyquist frequency) 
f1 = 1/Fs*2; f2 = 4/Fs*2; 
% [b,a] = butter(n,Wn,ftype) 
filter_order = 4; 
[b,a] = butter(filter_order,[f1 f2]); 
data_filtered = filtfilt(b,a,data_resampled); 
A2_data = fft(data_filtered); A2 = abs(A2_data/L); 
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); 
maxi = max(A_data); 
k = find(A_data==maxi); 
disp(f(k)); 
disp('fase'); 
disp(A_data); 
