pkg load signal

%% Generate data as for example measured by smartphone
% time vector - 10 seconds data
t = 0:0.01:20; %[s]
% turn it into a vector with non fixed time step as in smartphones
a = -1; b = 1;
t = t + (a + (b-a).*rand(1,length(t))).*1e-3;
f1 = 2; f2 = 8; % two frequencies within the signal
data = sin(2*pi*f1.*t)+0.5.*sin(2*pi*f2.*t);

%% Step 1: resample at fixed time step
Fs = 100.; % desired (fixed) sample rate
%[data_resampled,t_resampled] = resample(data,t,Fs);
t_resampled = t(1):1/Fs:t(end);
data_resampled = interp1(t, data, t_resampled, 'spline');
% to make sure time starts at t = 0s:
t_resampled = t_resampled - t_resampled(1);

subplot(2,1,1); plot(t_resampled, data_resampled); hold on
xlabel('Time [s]')
ylabel('Accelerations [m/s2]')

%% Step 2: Compute amplitude spectrum of the signal
L = length(data_resampled);
f = Fs*(0:(L/2))/L;
A2_data = fft(data_resampled); A2 = abs(A2_data/L);
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1);
subplot(2,1,2); plot(f,A_data); hold on
xlabel('Frequency [Hz]')
ylabel('Amplitude [m/s2/Hz]')

%% Step 3: Apply bandbass filter

% Lowerbound and upperbound cutoff bandpass filter (relative to Nyquist frequency)
f1 = 1/Fs*2; f2 = 4/Fs*2;
% [b,a] = butter(n,Wn,ftype)
filter_order = 4;
[b,a] = butter(filter_order,[f1 f2]);
data_filtered = filtfilt(b,a,data_resampled);
A2_data = fft(data_filtered); A2 = abs(A2_data/L);
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1);
% add to graph
subplot(2,1,2); plot(f,A_data);
legend('resampled data','filtered data')

subplot(2,1,1); plot(t_resampled,data_filtered)

disp(A2_data)
disp(A_data)
